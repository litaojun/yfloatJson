/*     */ package JMeter.plugins.functional.samplers.websocket;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import org.apache.jmeter.config.Arguments;
/*     */ import org.apache.jmeter.config.gui.ArgumentsPanel;
/*     */ import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
/*     */ import org.apache.jmeter.testelement.TestElement;
/*     */ import org.apache.jorphan.logging.LoggingManager;
/*     */ import org.apache.log.Logger;
/*     */ 
/*     */ public class WebSocketSamplerGui extends AbstractSamplerGui
/*     */ {
/*     */   private WebSocketSamplerPanel webSocketSamplerPanel;
/*  22 */   private static final Logger log = LoggingManager.getLoggerForClass();
/*     */ 
/*     */   public WebSocketSamplerGui()
/*     */   {
/*  26 */     init();
/*  27 */     initFields();
/*     */ 
/*  29 */     setLayout(new BorderLayout(0, 5));
/*  30 */     setBorder(makeBorder());
/*     */ 
/*  32 */     add(makeTitlePanel(), "North");
/*  33 */     add(this.webSocketSamplerPanel, "Center");
/*     */   }
/*     */ 
/*     */   public String getStaticLabel()
/*     */   {
/*  38 */     return "WebSocket Sampler";
/*     */   }
/*     */ 
/*     */   public String getLabelResource()
/*     */   {
/*  43 */     throw new IllegalStateException("This shouldn't be called");
/*     */   }
/*     */ 
/*     */   public void configure(TestElement element)
/*     */   {
/*  48 */     super.configure(element);
/*  49 */     if ((element instanceof WebSocketSampler)) {
/*  50 */       WebSocketSampler webSocketSamplerTestElement = (WebSocketSampler)element;
/*  51 */       this.webSocketSamplerPanel.setServerAddress(webSocketSamplerTestElement.getServerAddress());
/*  52 */       this.webSocketSamplerPanel.setServerPort(webSocketSamplerTestElement.getServerPort());
/*  53 */       this.webSocketSamplerPanel.setImplementation(webSocketSamplerTestElement.getImplementation());
/*  54 */       this.webSocketSamplerPanel.setProtocol(webSocketSamplerTestElement.getProtocol());
/*  55 */       this.webSocketSamplerPanel.setContextPath(webSocketSamplerTestElement.getContextPath());
/*  56 */       this.webSocketSamplerPanel.setContentEncoding(webSocketSamplerTestElement.getContentEncoding());
/*  57 */       this.webSocketSamplerPanel.setRequestPayload(webSocketSamplerTestElement.getRequestPayload());
/*  58 */       this.webSocketSamplerPanel.setResponseTimeout(webSocketSamplerTestElement.getResponseTimeout());
/*  59 */       this.webSocketSamplerPanel.setConnectionTimeout(webSocketSamplerTestElement.getConnectionTimeout());
/*  60 */       this.webSocketSamplerPanel.setIgnoreSslErrors(webSocketSamplerTestElement.isIgnoreSslErrors());
/*  61 */       this.webSocketSamplerPanel.setStreamingConnection(webSocketSamplerTestElement.isStreamingConnection());
/*  62 */       this.webSocketSamplerPanel.setConnectionId(webSocketSamplerTestElement.getConnectionId());
/*  63 */       this.webSocketSamplerPanel.setResponsePattern(webSocketSamplerTestElement.getResponsePattern());
/*  64 */       this.webSocketSamplerPanel.setCloseConncectionPattern(webSocketSamplerTestElement.getCloseConncectionPattern());
/*  65 */       this.webSocketSamplerPanel.setProxyAddress(webSocketSamplerTestElement.getProxyAddress());
/*  66 */       this.webSocketSamplerPanel.setProxyPassword(webSocketSamplerTestElement.getProxyPassword());
/*  67 */       this.webSocketSamplerPanel.setProxyPort(webSocketSamplerTestElement.getProxyPort());
/*  68 */       this.webSocketSamplerPanel.setProxyUsername(webSocketSamplerTestElement.getProxyUsername());
/*  69 */       this.webSocketSamplerPanel.setMessageBacklog(webSocketSamplerTestElement.getMessageBacklog());
/*     */ 
/*  71 */       Arguments queryStringParameters = webSocketSamplerTestElement.getQueryStringParameters();
/*  72 */       if (queryStringParameters != null)
/*  73 */         this.webSocketSamplerPanel.getAttributePanel().configure(queryStringParameters);
/*     */     }
/*     */   }
/*     */ 
/*     */   public TestElement createTestElement()
/*     */   {
/*  80 */     WebSocketSampler preproc = new WebSocketSampler();
/*  81 */     configureTestElement(preproc);
/*  82 */     return preproc;
/*     */   }
/*     */ 
/*     */   public void modifyTestElement(TestElement te)
/*     */   {
/*  87 */     configureTestElement(te);
/*  88 */     if ((te instanceof WebSocketSampler)) {
/*  89 */       WebSocketSampler webSocketSamplerTestElement = (WebSocketSampler)te;
/*  90 */       webSocketSamplerTestElement.setServerAddress(this.webSocketSamplerPanel.getServerAddress());
/*  91 */       webSocketSamplerTestElement.setServerPort(this.webSocketSamplerPanel.getServerPort());
/*  92 */       webSocketSamplerTestElement.setImplementation(this.webSocketSamplerPanel.getImplementation());
/*  93 */       webSocketSamplerTestElement.setProtocol(this.webSocketSamplerPanel.getProtocol());
/*  94 */       webSocketSamplerTestElement.setContextPath(this.webSocketSamplerPanel.getContextPath());
/*  95 */       webSocketSamplerTestElement.setContentEncoding(this.webSocketSamplerPanel.getContentEncoding());
/*  96 */       webSocketSamplerTestElement.setRequestPayload(this.webSocketSamplerPanel.getRequestPayload());
/*  97 */       webSocketSamplerTestElement.setConnectionTimeout(this.webSocketSamplerPanel.getConnectionTimeout());
/*  98 */       webSocketSamplerTestElement.setResponseTimeout(this.webSocketSamplerPanel.getResponseTimeout());
/*  99 */       webSocketSamplerTestElement.setIgnoreSslErrors(this.webSocketSamplerPanel.isIgnoreSslErrors());
/* 100 */       webSocketSamplerTestElement.setStreamingConnection(this.webSocketSamplerPanel.isStreamingConnection());
/* 101 */       webSocketSamplerTestElement.setConnectionId(this.webSocketSamplerPanel.getConnectionId());
/* 102 */       webSocketSamplerTestElement.setResponsePattern(this.webSocketSamplerPanel.getResponsePattern());
/* 103 */       webSocketSamplerTestElement.setCloseConncectionPattern(this.webSocketSamplerPanel.getCloseConncectionPattern());
/* 104 */       webSocketSamplerTestElement.setProxyAddress(this.webSocketSamplerPanel.getProxyAddress());
/* 105 */       webSocketSamplerTestElement.setProxyPassword(this.webSocketSamplerPanel.getProxyPassword());
/* 106 */       webSocketSamplerTestElement.setProxyPort(this.webSocketSamplerPanel.getProxyPort());
/* 107 */       webSocketSamplerTestElement.setProxyUsername(this.webSocketSamplerPanel.getProxyUsername());
/* 108 */       webSocketSamplerTestElement.setMessageBacklog(this.webSocketSamplerPanel.getMessageBacklog());
/*     */ 
/* 110 */       ArgumentsPanel queryStringParameters = this.webSocketSamplerPanel.getAttributePanel();
/* 111 */       if (queryStringParameters != null)
/* 112 */         webSocketSamplerTestElement.setQueryStringParameters((Arguments)queryStringParameters.createTestElement());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void clearGui()
/*     */   {
/* 119 */     super.clearGui();
/* 120 */     initFields();
/*     */   }
/*     */ 
/*     */   private void init() {
/* 124 */     this.webSocketSamplerPanel = new WebSocketSamplerPanel();
/*     */   }
/*     */ 
/*     */   private void initFields() {
/* 128 */     this.webSocketSamplerPanel.initFields();
/*     */   }
/*     */ }

/* Location:           E:\apache-jmeter-2.12\lib\ext\JMeterWebSocketSampler-1.0.1.jar
 * Qualified Name:     JMeter.plugins.functional.samplers.websocket.WebSocketSamplerGui
 * JD-Core Version:    0.6.1
 */