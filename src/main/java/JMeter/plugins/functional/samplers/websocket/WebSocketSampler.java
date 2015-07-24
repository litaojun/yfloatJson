/*     */ package JMeter.plugins.functional.samplers.websocket;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.TimeUnit;

/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.jmeter.config.Argument;
/*     */ import org.apache.jmeter.config.Arguments;
/*     */ import org.apache.jmeter.protocol.http.util.HTTPArgument;
/*     */ import org.apache.jmeter.samplers.AbstractSampler;
/*     */ import org.apache.jmeter.samplers.Entry;
/*     */ import org.apache.jmeter.samplers.SampleResult;
/*     */ import org.apache.jmeter.testelement.TestStateListener;
/*     */ import org.apache.jmeter.testelement.property.JMeterProperty;
/*     */ import org.apache.jmeter.testelement.property.PropertyIterator;
/*     */ import org.apache.jmeter.testelement.property.StringProperty;
/*     */ import org.apache.jmeter.testelement.property.TestElementProperty;
/*     */ import org.apache.jorphan.logging.LoggingManager;
/*     */ import org.apache.jorphan.util.JOrphanUtils;
/*     */ import org.apache.log.Logger;
/*     */ import org.eclipse.jetty.util.ssl.SslContextFactory;
/*     */ import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
/*     */ import org.eclipse.jetty.websocket.client.WebSocketClient;
/*     */ 
/*     */ public class WebSocketSampler extends AbstractSampler
/*     */   implements TestStateListener
/*     */ {
/*  40 */   private static final Logger log = LoggingManager.getLoggerForClass();
/*     */   private static final String ARG_VAL_SEP = "=";
/*     */   private static final String QRY_SEP = "&";
/*     */   private static final String WS_PREFIX = "ws://";
/*     */   private static final String WSS_PREFIX = "wss://";
/*     */   private static final String DEFAULT_PROTOCOL = "ws";
/*     */   private static Map<String, ServiceSocket> connectionList;
             
/*     */ 
/*     */   public WebSocketSampler()
/*     */   {
/*  52 */     setName("WebSocket sampler");
/*     */   }
/*     */ 
/*     */   private ServiceSocket getConnectionSocket() throws URISyntaxException, Exception {
/*  56 */     URI uri = getUri();
/*     */ 
/*  58 */     String connectionId = getThreadName() + getConnectionId();
/*     */     ServiceSocket socket;
/*  61 */     if (isStreamingConnection().booleanValue()) {
/*  62 */       if (connectionList.containsKey(connectionId)) {
/*  63 */          socket = (ServiceSocket)connectionList.get(connectionId);
/*  64 */         socket.initialize();
/*  65 */         return socket;
/*     */       }
/*  67 */        socket = new ServiceSocket(this);
/*  68 */       connectionList.put(connectionId, socket);
/*     */     }
/*     */     else {
/*  71 */       socket = new ServiceSocket(this);
/*     */     }
/*     */ 
/*  74 */     SslContextFactory sslContexFactory = new SslContextFactory();
/*  75 */     sslContexFactory.setTrustAll(isIgnoreSslErrors().booleanValue());
/*  76 */     WebSocketClient webSocketClient = new WebSocketClient(sslContexFactory);
/*     */ 
/*  78 */     webSocketClient.start();
/*  79 */     ClientUpgradeRequest request = new ClientUpgradeRequest();
/*  80 */     webSocketClient.connect(socket, uri, request);
/*     */ 
/*  82 */     int connectionTimeout = Integer.parseInt(getConnectionTimeout());
/*  83 */     socket.awaitOpen(connectionTimeout, TimeUnit.MILLISECONDS);
/*     */ 
/*  85 */     return socket;
/*     */   }
/*     */ 
/*     */   public SampleResult sample(Entry entry)
/*     */   {
/*  90 */     ServiceSocket socket = null;
/*  91 */     SampleResult sampleResult = new SampleResult();
/*  92 */     sampleResult.setSampleLabel(getName());
/*  93 */     sampleResult.setDataEncoding(getContentEncoding());
/*     */ 
/*  95 */     StringBuilder errorList = new StringBuilder();
/*  96 */     errorList.append("\n\n[Problems]\n");
/*     */ 
/*  98 */     boolean isOK = false;
/*     */ 
/* 100 */     String payloadMessage = getRequestPayload();
/* 101 */     sampleResult.setSamplerData(payloadMessage);
/* 102 */     sampleResult.sampleStart();
/*     */     try
/*     */     {
/* 105 */       socket = getConnectionSocket();
/* 106 */       if (socket == null) {
/* 107 */         sampleResult.setResponseCode("500");
/* 108 */         sampleResult.setSuccessful(false);
/* 109 */         sampleResult.sampleEnd();
/* 110 */         sampleResult.setResponseMessage(errorList.toString());
/* 111 */         errorList.append(" - Connection couldn't be opened").append("\n");
/* 112 */         return sampleResult;
/*     */       }
/*     */ 
/* 115 */       if (!payloadMessage.isEmpty()) {
/* 116 */         socket.sendMessage(payloadMessage);
/*     */       }
/*     */ 
/* 119 */       int responseTimeout = Integer.parseInt(getResponseTimeout());
/* 120 */       socket.awaitClose(responseTimeout, TimeUnit.MILLISECONDS);
/*     */ 
/* 123 */       if ((socket.getResponseMessage() == null) || (socket.getResponseMessage().isEmpty())) {
/* 124 */         sampleResult.setResponseCode("204");
/*     */       }
/*     */ 
/* 127 */       if (socket.getError().intValue() != 0) {
/* 128 */         isOK = false;
/* 129 */         sampleResult.setResponseCode(socket.getError().toString());
/*     */       } else {
/* 131 */         sampleResult.setResponseCodeOK();
/* 132 */         isOK = true;
/*     */       }
/*     */ 
/* 135 */       sampleResult.setResponseData(socket.getResponseMessage(), getContentEncoding());
/*     */     }
/*     */     catch (URISyntaxException e) {
/* 138 */       errorList.append(" - Invalid URI syntax: ").append(e.getMessage()).append("\n").append(StringUtils.join(e.getStackTrace(), "\n")).append("\n");
/*     */     } catch (IOException e) {
/* 140 */       errorList.append(" - IO Exception: ").append(e.getMessage()).append("\n").append(StringUtils.join(e.getStackTrace(), "\n")).append("\n");
/*     */     } catch (NumberFormatException e) {
/* 142 */       errorList.append(" - Cannot parse number: ").append(e.getMessage()).append("\n").append(StringUtils.join(e.getStackTrace(), "\n")).append("\n");
/*     */     } catch (InterruptedException e) {
/* 144 */       errorList.append(" - Execution interrupted: ").append(e.getMessage()).append("\n").append(StringUtils.join(e.getStackTrace(), "\n")).append("\n");
/*     */     } catch (Exception e) {
/* 146 */       errorList.append(" - Unexpected error: ").append(e.getMessage()).append("\n").append(StringUtils.join(e.getStackTrace(), "\n")).append("\n");
/*     */     }
/*     */ 
/* 149 */     sampleResult.sampleEnd();
/* 150 */     sampleResult.setSuccessful(isOK);
/*     */ 
/* 152 */     String logMessage = socket != null ? socket.getLogMessage() : "";
/* 153 */     sampleResult.setResponseMessage(logMessage + errorList);
/* 154 */     return sampleResult;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 159 */     if (name != null)
/* 160 */       setProperty("TestElement.name", name);
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 166 */     return getPropertyAsString("TestElement.name");
/*     */   }
/*     */ 
/*     */   public void setComment(String comment)
/*     */   {
/* 171 */     setProperty(new StringProperty("TestPlan.comments", comment));
/*     */   }
/*     */ 
/*     */   public String getComment()
/*     */   {
/* 176 */     return getProperty("TestPlan.comments").getStringValue();
/*     */   }
/*     */ 
/*     */   public URI getUri() throws URISyntaxException {
/* 180 */     String path = getContextPath();
/*     */ 
/* 182 */     if ((path.startsWith("ws://")) || 
/* 183 */       (path.startsWith("wss://"))) {
/* 184 */       return new URI(path);
/*     */     }
/* 186 */     String domain = getServerAddress();
/* 187 */     String protocol = getProtocol();
/*     */ 
/* 189 */     if (!path.startsWith("/")) {
/* 190 */       path = "/" + path;
/*     */     }
/*     */ 
/* 193 */     String queryString = getQueryString(getContentEncoding());
/* 194 */     if (isProtocolDefaultPort()) {
/* 195 */       return new URI(protocol, null, domain, -1, path, queryString, null);
/*     */     }
/* 197 */     return new URI(protocol, null, domain, Integer.parseInt(getServerPort()), path, queryString, null);
/*     */   }
/*     */ 
/*     */   public boolean isProtocolDefaultPort()
/*     */   {
/* 207 */     int port = Integer.parseInt(getServerPort());
/* 208 */     String protocol = getProtocol();
/* 209 */     return (("ws".equalsIgnoreCase(protocol)) && (port == 80)) || (
/* 210 */       ("wss".equalsIgnoreCase(protocol)) && (port == 443));
/*     */   }
/* 214 */   public String getServerPort() { String port_s = getPropertyAsString("serverPort", "0");
/*     */ 
/* 216 */     String protocol = getProtocol();
/*     */     Integer port;
/*     */     try {
/* 219 */       port = Integer.valueOf(Integer.parseInt(port_s));
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/*     */      // Integer port;
/* 221 */       port = Integer.valueOf(0);
/*     */     }
/*     */ 
/* 224 */     if (port.intValue() == 0) {
/* 225 */       if ("wss".equalsIgnoreCase(protocol))
/* 226 */         return String.valueOf(443);
/* 227 */       if ("ws".equalsIgnoreCase(protocol)) {
/* 228 */         return String.valueOf(80);
/*     */       }
/*     */     }
/* 231 */     return port.toString(); }
/*     */ 
/*     */   public void setServerPort(String port)
/*     */   {
/* 235 */     setProperty("serverPort", port);
/*     */   }
/*     */ 
/*     */   public String getResponseTimeout() {
/* 239 */     return getPropertyAsString("responseTimeout", "20000");
/*     */   }
/*     */ 
/*     */   public void setResponseTimeout(String responseTimeout) {
/* 243 */     setProperty("responseTimeout", responseTimeout);
/*     */   }
/*     */ 
/*     */   public String getConnectionTimeout()
/*     */   {
/* 248 */     return getPropertyAsString("connectionTimeout", "5000");
/*     */   }
/*     */ 
/*     */   public void setConnectionTimeout(String connectionTimeout) {
/* 252 */     setProperty("connectionTimeout", connectionTimeout);
/*     */   }
/*     */ 
/*     */   public void setProtocol(String protocol) {
/* 256 */     setProperty("protocol", protocol);
/*     */   }
/*     */ 
/*     */   public String getProtocol() {
/* 260 */     String protocol = getPropertyAsString("protocol");
/* 261 */     if ((protocol == null) || (protocol.isEmpty())) {
/* 262 */       return "ws";
/*     */     }
/* 264 */     return protocol;
/*     */   }
/*     */ 
/*     */   public void setServerAddress(String serverAddress) {
/* 268 */     setProperty("serverAddress", serverAddress);
/*     */   }
/*     */ 
/*     */   public String getServerAddress() {
/* 272 */     return getPropertyAsString("serverAddress");
/*     */   }
/*     */ 
/*     */   public void setImplementation(String implementation)
/*     */   {
/* 277 */     setProperty("implementation", implementation);
/*     */   }
/*     */ 
/*     */   public String getImplementation() {
/* 281 */     return getPropertyAsString("implementation");
/*     */   }
/*     */ 
/*     */   public void setContextPath(String contextPath) {
/* 285 */     setProperty("contextPath", contextPath);
/*     */   }
/*     */ 
/*     */   public String getContextPath() {
/* 289 */     return getPropertyAsString("contextPath");
/*     */   }
/*     */ 
/*     */   public void setContentEncoding(String contentEncoding) {
/* 293 */     setProperty("contentEncoding", contentEncoding);
/*     */   }
/*     */ 
/*     */   public String getContentEncoding() {
/* 297 */     return getPropertyAsString("contentEncoding", "UTF-8");
/*     */   }
/*     */ 
/*     */   public void setRequestPayload(String requestPayload) {
/* 301 */     setProperty("requestPayload", requestPayload);
/*     */   }
/*     */ 
/*     */   public String getRequestPayload() {
/* 305 */     return getPropertyAsString("requestPayload");
/*     */   }
/*     */ 
/*     */   public void setIgnoreSslErrors(Boolean ignoreSslErrors) {
/* 309 */     setProperty("ignoreSslErrors", ignoreSslErrors.booleanValue());
/*     */   }
/*     */ 
/*     */   public Boolean isIgnoreSslErrors() {
/* 313 */     return Boolean.valueOf(getPropertyAsBoolean("ignoreSslErrors"));
/*     */   }
/*     */ 
/*     */   public void setStreamingConnection(Boolean streamingConnection) {
/* 317 */     setProperty("streamingConnection", streamingConnection.booleanValue());
/*     */   }
/*     */ 
/*     */   public Boolean isStreamingConnection() {
/* 321 */     return Boolean.valueOf(getPropertyAsBoolean("streamingConnection"));
/*     */   }
/*     */ 
/*     */   public void setConnectionId(String connectionId) {
/* 325 */     setProperty("connectionId", connectionId);
/*     */   }
/*     */ 
/*     */   public String getConnectionId() {
/* 329 */     return getPropertyAsString("connectionId");
/*     */   }
/*     */ 
/*     */   public void setResponsePattern(String responsePattern) {
/* 333 */     setProperty("responsePattern", responsePattern);
/*     */   }
/*     */ 
/*     */   public String getResponsePattern() {
/* 337 */     return getPropertyAsString("responsePattern");
/*     */   }
/*     */ 
/*     */   public void setCloseConncectionPattern(String closeConncectionPattern) {
/* 341 */     setProperty("closeConncectionPattern", closeConncectionPattern);
/*     */   }
/*     */ 
/*     */   public String getCloseConncectionPattern() {
/* 345 */     return getPropertyAsString("closeConncectionPattern");
/*     */   }
/*     */ 
/*     */   public void setProxyAddress(String proxyAddress) {
/* 349 */     setProperty("proxyAddress", proxyAddress);
/*     */   }
/*     */ 
/*     */   public String getProxyAddress() {
/* 353 */     return getPropertyAsString("proxyAddress");
/*     */   }
/*     */ 
/*     */   public void setProxyPassword(String proxyPassword) {
/* 357 */     setProperty("proxyPassword", proxyPassword);
/*     */   }
/*     */ 
/*     */   public String getProxyPassword() {
/* 361 */     return getPropertyAsString("proxyPassword");
/*     */   }
/*     */ 
/*     */   public void setProxyPort(String proxyPort) {
/* 365 */     setProperty("proxyPort", proxyPort);
/*     */   }
/*     */ 
/*     */   public String getProxyPort() {
/* 369 */     return getPropertyAsString("proxyPort");
/*     */   }
/*     */ 
/*     */   public void setProxyUsername(String proxyUsername) {
/* 373 */     setProperty("proxyUsername", proxyUsername);
/*     */   }
/*     */ 
/*     */   public String getProxyUsername() {
/* 377 */     return getPropertyAsString("proxyUsername");
/*     */   }
/*     */ 
/*     */   public void setMessageBacklog(String messageBacklog) {
/* 381 */     setProperty("messageBacklog", messageBacklog);
/*     */   }
/*     */ 
/*     */   public String getMessageBacklog() {
/* 385 */     return getPropertyAsString("messageBacklog", "3");
/*     */   }
/*     */ 
/*     */   public String getQueryString(String contentEncoding)
/*     */   {
/* 392 */     if (JOrphanUtils.isBlank(contentEncoding))
/*     */     {
/* 394 */       contentEncoding = "UTF-8";
/*     */     }
/* 396 */     StringBuilder buf = new StringBuilder();
/* 397 */     PropertyIterator iter = getQueryStringParameters().iterator();
/* 398 */     boolean first = true;
/* 399 */     while (iter.hasNext()) {
/* 400 */       HTTPArgument item = null;
/* 401 */       Object objectValue = iter.next().getObjectValue();
/*     */       try {
/* 403 */         item = (HTTPArgument)objectValue;
/*     */       } catch (ClassCastException e) {
/* 405 */         item = new HTTPArgument((Argument)objectValue);
/*     */       }
/* 407 */       String encodedName = item.getEncodedName();
/* 408 */       if (encodedName.length() != 0)
/*     */       {
/* 411 */         if (!first)
/* 412 */           buf.append("&");
/*     */         else {
/* 414 */           first = false;
/*     */         }
/* 416 */         buf.append(encodedName);
/* 417 */         if (item.getMetaData() == null)
/* 418 */           buf.append("=");
/*     */         else {
/* 420 */           buf.append(item.getMetaData());
/*     */         }
/*     */ 
/*     */         try
/*     */         {
/* 425 */           buf.append(item.getEncodedValue(contentEncoding));
/*     */         } catch (UnsupportedEncodingException e) {
/* 427 */           log.warn("Unable to encode parameter in encoding " + contentEncoding + ", parameter value not included in query string");
/*     */         }
/*     */       }
/*     */     }
/* 430 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   public void setQueryStringParameters(Arguments queryStringParameters) {
/* 434 */     setProperty(new TestElementProperty("queryStringParameters", queryStringParameters));
/*     */   }
/*     */ 
/*     */   public Arguments getQueryStringParameters() {
/* 438 */     Arguments args = (Arguments)getProperty("queryStringParameters").getObjectValue();
/* 439 */     return args;
/*     */   }
/*     */ 
/*     */   public void testStarted()
/*     */   {
/* 445 */     testStarted("unknown");
/*     */   }
/*     */ 
/*     */   public void testStarted(String host)
/*     */   {
/* 450 */     connectionList = new ConcurrentHashMap();
/*     */   }
/*     */ 
/*     */   public void testEnded()
/*     */   {
/* 455 */     testEnded("unknown");
/*     */   }
/*     */ 
/*     */   public void testEnded(String host)
/*     */   {
/* 460 */     for (ServiceSocket socket : connectionList.values())
/* 461 */       socket.close();
/*     */   }
public void send(String strReq) throws URISyntaxException,IOException,Exception
{
	this.getConnectionSocket().sendMessage(strReq);
//	for(ServiceSocket x:WebSocketSampler.connectionList.values())
//	{
//			System.out.println("22222222222-2");
//			x.sendMessage(strReq);
//			System.out.println("22222222222-3");
//	}
}
/*     */ }

