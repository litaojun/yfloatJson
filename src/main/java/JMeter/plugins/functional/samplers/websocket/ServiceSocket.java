/*     */ package JMeter.plugins.functional.samplers.websocket;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.charset.CharacterCodingException;
/*     */ import java.util.Deque;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.concurrent.CountDownLatch;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;

/*     */ import org.apache.jmeter.engine.util.CompoundVariable;
/*     */ import org.apache.jorphan.logging.LoggingManager;
/*     */ import org.apache.log.Logger;
/*     */ import org.eclipse.jetty.websocket.api.RemoteEndpoint;
/*     */ import org.eclipse.jetty.websocket.api.Session;
/*     */ import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
/*     */ import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
/*     */ import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
/*     */ import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import com.gw.dzhyun.util.TranYfloatMain;
/*     */ 
/*     */ @WebSocket(maxTextMessageSize=268435456)
/*     */ public class ServiceSocket
/*     */ {
/*     */   private final WebSocketSampler parent;
/*  40 */   private static final Logger log = LoggingManager.getLoggerForClass();
/*  41 */   Deque<String> responeBacklog = new LinkedList();
/*  42 */   private Integer error = Integer.valueOf(0);
/*  43 */   private StringBuffer logMessage = new StringBuffer();
/*  44 */   private CountDownLatch openLatch = new CountDownLatch(1);
/*  45 */   private CountDownLatch closeLatch = new CountDownLatch(1);
/*  46 */   private Session session = null;
/*     */   private String responsePattern;
/*     */   private String disconnectPattern;
/*  49 */   private int messageCounter = 1;
/*     */   private Pattern responseExpression;
/*     */   private Pattern disconnectExpression;
/*  52 */   private boolean connected = false;
/*     */ 
/*     */   public ServiceSocket(WebSocketSampler parent) {
/*  55 */     this.parent = parent;
/*  56 */     this.responsePattern = new CompoundVariable(parent.getResponsePattern()).execute();
/*  57 */     this.disconnectPattern = new CompoundVariable(parent.getCloseConncectionPattern()).execute();
/*  58 */     this.logMessage.append("\n\n[Execution Flow]\n");
/*  59 */     this.logMessage.append(" - Opening new connection\n");
/*  60 */     initializePatterns();
/*     */   }
/*     */ 
/*     */   @OnWebSocketMessage
/*     */   public void onMessage(String msg) {
/*  65 */     synchronized (this.parent) {
/*  66 */       log.debug("Received message: " + msg);
/*  67 */       String length = " (" + msg.length() + " bytes)";
/*  68 */       this.logMessage.append(" - Received message #").append(this.messageCounter).append(length);
/*  69 */       addResponseMessage("[Message " + this.messageCounter++ + "]\n" + msg + "\n\n");
/*     */ 
/*  71 */       if ((this.responseExpression == null) || (this.responseExpression.matcher(msg).find())) {
/*  72 */         this.logMessage.append("; matched response pattern").append("\n");
/*  73 */         this.closeLatch.countDown();
/*  74 */       } else if ((!this.disconnectPattern.isEmpty()) && (this.disconnectExpression.matcher(msg).find())) {
/*  75 */         this.logMessage.append("; matched connection close pattern").append("\n");
/*  76 */         this.closeLatch.countDown();
/*  77 */         close(1000, "JMeter closed session.");
/*     */       } else {
/*  79 */         this.logMessage.append("; didn't match any pattern").append("\n");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   @OnWebSocketMessage
/*     */   public void onMessageBinary(byte[] buf, int offset, int len)
/*     */     throws CharacterCodingException
/*     */   {
/*  89 */     synchronized (this.parent)
/*     */     {
/*  91 */       String msg = new String(buf, offset, len);
                    log.debug("Received message: " + msg);
                    this.logMessage.append(" 1111111");
                    TranYfloatMain tyfloat = new TranYfloatMain(msg);
                    this.logMessage.append(" 222222");
                    msg = tyfloat.dealJsonArray().toJSONString();
                    this.logMessage.append(" 33333");
/*  92 */       log.debug("Received message: " + msg);
/*  93 */       String length = " (" + msg.length() + " bytes)";
/*  94 */       this.logMessage.append(" - Received message #").append(this.messageCounter).append(length);
/*  95 */       addResponseMessage("[Message " + this.messageCounter++ + "]\n" + msg + "\n\n");
/*     */ 
/*  97 */       if ((this.responseExpression == null) || (this.responseExpression.matcher(msg).find())) {
/*  98 */         this.logMessage.append("; matched response pattern").append("\n");
/*  99 */         this.closeLatch.countDown();
/* 100 */       } else if ((!this.disconnectPattern.isEmpty()) && (this.disconnectExpression.matcher(msg).find())) {
/* 101 */         this.logMessage.append("; matched connection close pattern").append("\n");
/* 102 */         this.closeLatch.countDown();
/* 103 */         close(1000, "JMeter closed session.");
/*     */       } else {
/* 105 */         this.logMessage.append("; didn't match any pattern").append("\n");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   @OnWebSocketConnect
/*     */   public void onOpen(Session session)
/*     */   {
/* 113 */     this.logMessage.append(" - WebSocket conection has been opened").append("\n");
/* 114 */     log.debug("Connect " + session.isOpen());
/* 115 */     this.session = session;
/* 116 */     this.connected = true;
/* 117 */     this.openLatch.countDown();
/*     */   }
/*     */ 
/*     */   @OnWebSocketClose
/*     */   public void onClose(int statusCode, String reason) {
/* 122 */     if (statusCode != 1000) {
/* 123 */       log.error("Disconnect " + statusCode + ": " + reason);
/* 124 */       this.logMessage.append(" - WebSocket conection closed unexpectedly by the server: [").append(statusCode).append("] ").append(reason).append("\n");
/* 125 */       this.error = Integer.valueOf(statusCode);
/*     */     } else {
/* 127 */       this.logMessage.append(" - WebSocket conection has been successfully closed by the server").append("\n");
/* 128 */       log.debug("Disconnect " + statusCode + ": " + reason);
/*     */     }
/* 130 */     this.openLatch.countDown();
/* 131 */     this.closeLatch.countDown();
/* 132 */     this.connected = false;
/*     */   }
/*     */ 
/*     */   public String getResponseMessage()
/*     */   {
/* 139 */     String responseMessage = "";
/* 140 */     Iterator iterator = this.responeBacklog.iterator();
/* 141 */     while (iterator.hasNext()) {
/* 142 */       responseMessage = responseMessage + (String)iterator.next();
/*     */     }
/*     */ 
/* 145 */     return responseMessage;
/*     */   }
/*     */ 
/*     */   public boolean awaitClose(int duration, TimeUnit unit) throws InterruptedException {
/* 149 */     this.logMessage.append(" - Waiting for messages for ").append(duration).append(" ").append(unit.toString()).append("\n");
/* 150 */     boolean res = this.closeLatch.await(duration, unit);
/*     */ 
/* 152 */     if (!this.parent.isStreamingConnection().booleanValue())
/* 153 */       close(1000, "JMeter closed session.");
/*     */     else {
/* 155 */       this.logMessage.append(" - Leaving streaming connection open").append("\n");
/*     */     }
/*     */ 
/* 158 */     return res;
/*     */   }
/*     */ 
/*     */   public boolean awaitOpen(int duration, TimeUnit unit) throws InterruptedException {
/* 162 */     this.logMessage.append(" - Waiting for the server connection for ").append(duration).append(" ").append(unit.toString()).append("\n");
/* 163 */     boolean res = this.openLatch.await(duration, unit);
/*     */ 
/* 165 */     if (this.connected)
/* 166 */       this.logMessage.append(" - Connection established").append("\n");
/*     */     else {
/* 168 */       this.logMessage.append(" - Cannot connect to the remote server").append("\n");
/*     */     }
/*     */ 
/* 171 */     return res;
/*     */   }
/*     */ 
/*     */   public Session getSession()
/*     */   {
/* 178 */     return this.session;
/*     */   }
/*     */ 
/*     */   public void sendMessage(String message) throws IOException {
/* 182 */     this.session.getRemote().sendString(message);
/*     */   }
/*     */ 
/*     */   public void close() {
/* 186 */     close(1000, "JMeter closed session.");
/*     */   }
/*     */ 
/*     */   public void close(int statusCode, String statusText) {
/* 190 */     if (this.session != null) {
/* 191 */       this.session.close(statusCode, statusText);
/*     */     }
/* 193 */     this.logMessage.append(" - WebSocket conection closed by the client").append("\n");
/*     */   }
/*     */ 
/*     */   public Integer getError()
/*     */   {
/* 200 */     return this.error;
/*     */   }
/*     */ 
/*     */   public String getLogMessage()
/*     */   {
/* 207 */     this.logMessage.append("\n\n[Variables]\n");
/* 208 */     this.logMessage.append(" - Message count: ").append(this.messageCounter - 1).append("\n");
/*     */ 
/* 210 */     return this.logMessage.toString();
/*     */   }
/*     */ 
/*     */   public void log(String message) {
/* 214 */     this.logMessage.append(message);
/*     */   }
/*     */ 
/*     */   private void initializePatterns() {
/*     */     try {
/* 219 */       this.logMessage.append(" - Using response message pattern \"").append(this.responsePattern).append("\"\n");
/* 220 */       this.responseExpression = ((this.responsePattern != null) || (!this.responsePattern.isEmpty()) ? Pattern.compile(this.responsePattern) : null);
/*     */     } catch (Exception ex) {
/* 222 */       this.logMessage.append(" - Invalid response message regular expression pattern: ").append(ex.getLocalizedMessage()).append("\n");
/* 223 */       log.error("Invalid response message regular expression pattern: " + ex.getLocalizedMessage());
/* 224 */       this.responseExpression = null;
/*     */     }
/*     */     try
/*     */     {
/* 228 */       this.logMessage.append(" - Using disconnect pattern \"").append(this.disconnectPattern).append("\"\n");
/* 229 */       this.disconnectExpression = ((this.disconnectPattern != null) || (!this.disconnectPattern.isEmpty()) ? Pattern.compile(this.disconnectPattern) : null);
/*     */     } catch (Exception ex) {
/* 231 */       this.logMessage.append(" - Invalid disconnect regular expression pattern: ").append(ex.getLocalizedMessage()).append("\n");
/* 232 */       log.error("Invalid disconnect regular regular expression pattern: " + ex.getLocalizedMessage());
/* 233 */       this.disconnectExpression = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isConnected()
/*     */   {
/* 242 */     return this.connected;
/*     */   }
/*     */ 
/*     */   public void initialize() {
/* 246 */     this.logMessage = new StringBuffer();
/* 247 */     this.logMessage.append("\n\n[Execution Flow]\n");
/* 248 */     this.logMessage.append(" - Reusing exising connection\n");
/* 249 */     this.error = Integer.valueOf(0);
/*     */ 
/* 251 */     this.closeLatch = new CountDownLatch(1);
/*     */   }
/*     */ 
/*     */   private void addResponseMessage(String message) {
/*     */     int messageBacklog;
/*     */     try {
/* 257 */       messageBacklog = Integer.parseInt(this.parent.getMessageBacklog());
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/*     */       //int messageBacklog;
/* 259 */       this.logMessage.append(" - Message backlog value not set; using default 3\n");
/* 260 */       messageBacklog = 1;
/*     */     }
/*     */ 
/* 263 */     while (this.responeBacklog.size() >= messageBacklog) {
/* 264 */       this.responeBacklog.poll();
/*     */     }
/* 266 */     this.responeBacklog.add(message);
/*     */   }
/*     */ }

/* Location:           E:\apache-jmeter-2.12\lib\ext\JMeterWebSocketSampler-1.0.1.jar
 * Qualified Name:     JMeter.plugins.functional.samplers.websocket.ServiceSocket
 * JD-Core Version:    0.6.1
 */