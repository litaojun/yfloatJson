/*     */ package JMeter.plugins.functional.samplers.websocket;
/*     */ 
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.GroupLayout.Alignment;
/*     */ import javax.swing.GroupLayout.ParallelGroup;
/*     */ import javax.swing.GroupLayout.SequentialGroup;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JEditorPane;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextField;
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;

/*     */ import org.apache.jmeter.config.gui.ArgumentsPanel;
/*     */ import org.apache.jmeter.protocol.http.gui.HTTPArgumentsPanel;
/*     */ import org.apache.jorphan.logging.LoggingManager;
/*     */ import org.apache.log.Logger;
/*     */ 
/*     */ public class WebSocketSamplerPanel extends JPanel
/*     */ {
/*  18 */   private static final Logger log = LoggingManager.getLoggerForClass();
/*     */   private HTTPArgumentsPanel attributePanel;
/*     */   private JTextField closeConncectionPatternTextField;
/*     */   private JTextField connectionIdTextField;
/*     */   private JTextField connectionTimeoutTextField;
/*     */   private JTextField contentEncodingTextField;
/*     */   private JTextField contextPathTextField;
/*     */   private JCheckBox ignoreSslErrorsCheckBox;
/*     */   private JComboBox implementationComboBox;
/*     */   private JLabel jLabel1;
/*     */   private JLabel jLabel10;
/*     */   private JLabel jLabel11;
/*     */   private JLabel jLabel12;
/*     */   private JLabel jLabel13;
/*     */   private JLabel jLabel14;
/*     */   private JLabel jLabel15;
/*     */   private JLabel jLabel16;
/*     */   private JLabel jLabel17;
/*     */   private JLabel jLabel2;
/*     */   private JLabel jLabel3;
/*     */   private JLabel jLabel4;
/*     */   private JLabel jLabel5;
/*     */   private JLabel jLabel6;
/*     */   private JLabel jLabel7;
/*     */   private JLabel jLabel8;
/*     */   private JLabel jLabel9;
/*     */   private JPanel jPanel1;
/*     */   private JPanel jPanel2;
/*     */   private JPanel jPanel3;
/*     */   private JPanel jPanel5;
/*     */   private JPanel jPanel6;
/*     */   private JScrollPane jScrollPane1;
/*     */   private JTextField messageBacklogTextField;
/*     */   private JTextField protocolTextField;
/*     */   private JTextField proxyAddressTextField;
/*     */   private JTextField proxyPasswordTextField;
/*     */   private JTextField proxyPortTextField;
/*     */   private JTextField proxyUsernameTextField;
/*     */   private JPanel querystringAttributesPanel;
/*     */   private JEditorPane requestPayloadEditorPane;
/*     */   private JTextField responsePatternTextField;
/*     */   private JTextField responseTimeoutTextField;
/*     */   private JTextField serverAddressTextField;
/*     */   private JTextField serverPortTextField;
/*     */   private JCheckBox streamingConnectionCheckBox;
/*     */ 
/*     */   public WebSocketSamplerPanel()
/*     */   {
/*  25 */     initComponents();
/*     */ 
/*  27 */     this.attributePanel = new HTTPArgumentsPanel();
/*  28 */     this.querystringAttributesPanel.add(this.attributePanel);
/*     */   }
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  40 */     this.jPanel1 = new JPanel();
/*  41 */     this.jLabel1 = new JLabel();
/*  42 */     this.serverAddressTextField = new JTextField();
/*  43 */     this.jLabel2 = new JLabel();
/*  44 */     this.serverPortTextField = new JTextField();
/*  45 */     this.jPanel2 = new JPanel();
/*  46 */     this.jLabel3 = new JLabel();
/*  47 */     this.connectionTimeoutTextField = new JTextField();
/*  48 */     this.jLabel17 = new JLabel();
/*  49 */     this.responseTimeoutTextField = new JTextField();
/*  50 */     this.jPanel3 = new JPanel();
/*  51 */     this.jLabel4 = new JLabel();
/*  52 */     this.jLabel5 = new JLabel();
/*  53 */     this.jLabel6 = new JLabel();
/*  54 */     this.contextPathTextField = new JTextField();
/*  55 */     this.protocolTextField = new JTextField();
/*  56 */     this.contentEncodingTextField = new JTextField();
/*  57 */     this.jLabel8 = new JLabel();
/*  58 */     this.connectionIdTextField = new JTextField();
/*  59 */     this.querystringAttributesPanel = new JPanel();
/*  60 */     this.ignoreSslErrorsCheckBox = new JCheckBox();
/*  61 */     this.jScrollPane1 = new JScrollPane();
/*  62 */     this.requestPayloadEditorPane = new JEditorPane();
/*  63 */     this.jLabel14 = new JLabel();
/*  64 */     this.jLabel15 = new JLabel();
/*  65 */     this.implementationComboBox = new JComboBox();
/*  66 */     this.streamingConnectionCheckBox = new JCheckBox();
/*  67 */     this.jPanel5 = new JPanel();
/*  68 */     this.jLabel7 = new JLabel();
/*  69 */     this.responsePatternTextField = new JTextField();
/*  70 */     this.jLabel9 = new JLabel();
/*  71 */     this.closeConncectionPatternTextField = new JTextField();
/*  72 */     this.jLabel16 = new JLabel();
/*  73 */     this.messageBacklogTextField = new JTextField();
/*  74 */     this.jPanel6 = new JPanel();
/*  75 */     this.jLabel10 = new JLabel();
/*  76 */     this.proxyAddressTextField = new JTextField();
/*  77 */     this.jLabel11 = new JLabel();
/*  78 */     this.proxyPortTextField = new JTextField();
/*  79 */     this.jLabel12 = new JLabel();
/*  80 */     this.proxyUsernameTextField = new JTextField();
/*  81 */     this.jLabel13 = new JLabel();
/*  82 */     this.proxyPasswordTextField = new JTextField();
/*     */ 
/*  84 */     this.jPanel1.setBorder(BorderFactory.createTitledBorder("Web Server"));
/*     */ 
/*  86 */     this.jLabel1.setText("Server Name or IP:");
/*     */ 
/*  88 */     this.jLabel2.setText("Port Number:");
/*     */ 
/*  90 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  91 */     this.jPanel1.setLayout(jPanel1Layout);
/*  92 */     jPanel1Layout.setHorizontalGroup(
/*  93 */       jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  94 */       .addGroup(jPanel1Layout.createSequentialGroup()
/*  95 */       .addContainerGap()
/*  96 */       .addComponent(this.jLabel1)
/*  97 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  98 */       .addComponent(this.serverAddressTextField)
/*  99 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 100 */       .addComponent(this.jLabel2)
/* 101 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 102 */       .addComponent(this.serverPortTextField, -2, 43, -2)
/* 103 */       .addContainerGap()));
/*     */ 
/* 105 */     jPanel1Layout.setVerticalGroup(
/* 106 */       jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 107 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 108 */       .addContainerGap()
/* 109 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 110 */       .addComponent(this.jLabel1)
/* 111 */       .addComponent(this.serverAddressTextField, -2, -1, -2)
/* 112 */       .addComponent(this.jLabel2)
/* 113 */       .addComponent(this.serverPortTextField, -2, -1, -2))
/* 114 */       .addContainerGap(-1, 32767)));
/*     */ 
/* 117 */     this.jPanel2.setBorder(BorderFactory.createTitledBorder("Timeout (milliseconds)"));
/*     */ 
/* 119 */     this.jLabel3.setText("Connection:");
/*     */ 
/* 121 */     this.jLabel17.setText("Response:");
/*     */ 
/* 123 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 124 */     this.jPanel2.setLayout(jPanel2Layout);
/* 125 */     jPanel2Layout.setHorizontalGroup(
/* 126 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 127 */       .addGroup(jPanel2Layout.createSequentialGroup()
/* 128 */       .addContainerGap()
/* 129 */       .addComponent(this.jLabel3)
/* 130 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 131 */       .addComponent(this.connectionTimeoutTextField)
/* 132 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 133 */       .addComponent(this.jLabel17)
/* 134 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 135 */       .addComponent(this.responseTimeoutTextField)
/* 136 */       .addContainerGap()));
/*     */ 
/* 138 */     jPanel2Layout.setVerticalGroup(
/* 139 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 140 */       .addGroup(jPanel2Layout.createSequentialGroup()
/* 141 */       .addContainerGap()
/* 142 */       .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 143 */       .addComponent(this.jLabel3)
/* 144 */       .addComponent(this.connectionTimeoutTextField, -2, -1, -2)
/* 145 */       .addComponent(this.jLabel17)
/* 146 */       .addComponent(this.responseTimeoutTextField, -2, -1, -2))
/* 147 */       .addContainerGap(-1, 32767)));
/*     */ 
/* 150 */     this.jPanel3.setBorder(BorderFactory.createTitledBorder("WebSocket Request"));
/*     */ 
/* 152 */     this.jLabel4.setText("Protocol [ws/wss]:");
/*     */ 
/* 154 */     this.jLabel5.setText("Path:");
/*     */ 
/* 156 */     this.jLabel6.setText("Content encoding:");
/*     */ 
/* 158 */     this.protocolTextField.setToolTipText("");
/*     */ 
/* 160 */     this.jLabel8.setText("Connection Id:");
/*     */ 
/* 162 */     this.querystringAttributesPanel.setLayout(new BoxLayout(this.querystringAttributesPanel, 2));
/*     */ 
/* 164 */     this.ignoreSslErrorsCheckBox.setText("Ignore SSL certificate errors");
/*     */ 
/* 166 */     this.jScrollPane1.setViewportView(this.requestPayloadEditorPane);
/*     */ 
/* 168 */     this.jLabel14.setText("Request data");
/*     */ 
/* 170 */     this.jLabel15.setText("Implementation:");
/*     */ 
/* 172 */     this.implementationComboBox.setModel(new DefaultComboBoxModel(new String[] { "RFC6455 (v13)" }));
/*     */ 
/* 174 */     this.streamingConnectionCheckBox.setText("Streaming connection");
/*     */ 
/* 176 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/* 177 */     this.jPanel3.setLayout(jPanel3Layout);
/* 178 */     jPanel3Layout.setHorizontalGroup(
/* 179 */       jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 180 */       .addGroup(jPanel3Layout.createSequentialGroup()
/* 181 */       .addContainerGap()
/* 182 */       .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 183 */       .addComponent(this.querystringAttributesPanel, -1, -1, 32767)
/* 184 */       .addComponent(this.jScrollPane1)
/* 185 */       .addGroup(jPanel3Layout.createSequentialGroup()
/* 186 */       .addComponent(this.jLabel15)
/* 187 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 188 */       .addComponent(this.implementationComboBox, 0, 1, 32767)
/* 189 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 190 */       .addComponent(this.jLabel4)
/* 191 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 192 */       .addComponent(this.protocolTextField, -2, 40, -2)
/* 193 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 194 */       .addComponent(this.jLabel6)
/* 195 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 196 */       .addComponent(this.contentEncodingTextField, -2, 40, -2)
/* 197 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 198 */       .addComponent(this.jLabel8)
/* 199 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 200 */       .addComponent(this.connectionIdTextField))
/* 201 */       .addGroup(jPanel3Layout.createSequentialGroup()
/* 202 */       .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 203 */       .addComponent(this.jLabel14)
/* 204 */       .addGroup(jPanel3Layout.createSequentialGroup()
/* 205 */       .addComponent(this.ignoreSslErrorsCheckBox)
/* 206 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 207 */       .addComponent(this.streamingConnectionCheckBox)))
/* 208 */       .addGap(0, 0, 32767))
/* 209 */       .addGroup(jPanel3Layout.createSequentialGroup()
/* 210 */       .addComponent(this.jLabel5)
/* 211 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 212 */       .addComponent(this.contextPathTextField)))
/* 213 */       .addContainerGap()));
/*     */ 
/* 215 */     jPanel3Layout.setVerticalGroup(
/* 216 */       jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 217 */       .addGroup(jPanel3Layout.createSequentialGroup()
/* 218 */       .addGap(10, 10, 10)
/* 219 */       .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 220 */       .addComponent(this.jLabel4)
/* 221 */       .addComponent(this.protocolTextField, -2, -1, -2)
/* 222 */       .addComponent(this.jLabel6)
/* 223 */       .addComponent(this.contentEncodingTextField, -2, -1, -2)
/* 224 */       .addComponent(this.jLabel8)
/* 225 */       .addComponent(this.connectionIdTextField, -2, -1, -2)
/* 226 */       .addComponent(this.jLabel15)
/* 227 */       .addComponent(this.implementationComboBox, -2, -1, -2))
/* 228 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 229 */       .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 230 */       .addComponent(this.jLabel5)
/* 231 */       .addComponent(this.contextPathTextField, -2, -1, -2))
/* 232 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 233 */       .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 234 */       .addComponent(this.ignoreSslErrorsCheckBox)
/* 235 */       .addComponent(this.streamingConnectionCheckBox))
/* 236 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 237 */       .addComponent(this.querystringAttributesPanel, -1, 102, 32767)
/* 238 */       .addGap(8, 8, 8)
/* 239 */       .addComponent(this.jLabel14)
/* 240 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 241 */       .addComponent(this.jScrollPane1, -1, 118, 32767)
/* 242 */       .addContainerGap()));
/*     */ 
/* 245 */     this.jPanel5.setBorder(BorderFactory.createTitledBorder("WebSocket Response"));
/*     */ 
/* 247 */     this.jLabel7.setText("Response pattern:");
/*     */ 
/* 249 */     this.jLabel9.setText("Close connection pattern:");
/*     */ 
/* 251 */     this.jLabel16.setText("Message backlog:");
/*     */ 
/* 253 */     GroupLayout jPanel5Layout = new GroupLayout(this.jPanel5);
/* 254 */     this.jPanel5.setLayout(jPanel5Layout);
/* 255 */     jPanel5Layout.setHorizontalGroup(
/* 256 */       jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 257 */       .addGroup(jPanel5Layout.createSequentialGroup()
/* 258 */       .addContainerGap()
/* 259 */       .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 260 */       .addGroup(jPanel5Layout.createSequentialGroup()
/* 261 */       .addComponent(this.jLabel7)
/* 262 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 263 */       .addComponent(this.responsePatternTextField)
/* 264 */       .addGap(18, 18, 18)
/* 265 */       .addComponent(this.jLabel16)
/* 266 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 267 */       .addComponent(this.messageBacklogTextField, -2, 40, -2))
/* 268 */       .addGroup(GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
/* 269 */       .addComponent(this.jLabel9)
/* 270 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 271 */       .addComponent(this.closeConncectionPatternTextField)))
/* 272 */       .addContainerGap()));
/*     */ 
/* 274 */     jPanel5Layout.setVerticalGroup(
/* 275 */       jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 276 */       .addGroup(jPanel5Layout.createSequentialGroup()
/* 277 */       .addContainerGap()
/* 278 */       .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 279 */       .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 280 */       .addComponent(this.jLabel16)
/* 281 */       .addComponent(this.messageBacklogTextField, -2, -1, -2))
/* 282 */       .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 283 */       .addComponent(this.jLabel7)
/* 284 */       .addComponent(this.responsePatternTextField, -2, -1, -2)))
/* 285 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 286 */       .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 287 */       .addComponent(this.jLabel9)
/* 288 */       .addComponent(this.closeConncectionPatternTextField, -2, -1, -2))
/* 289 */       .addContainerGap(-1, 32767)));
/*     */ 
/* 292 */     this.jPanel6.setBorder(BorderFactory.createTitledBorder("Proxy Server (currently not supported by Jetty)"));
/*     */ 
/* 294 */     this.jLabel10.setText("Server Name or IP:");
/*     */ 
/* 296 */     this.jLabel11.setText("Port Number:");
/*     */ 
/* 298 */     this.jLabel12.setText("Username:");
/*     */ 
/* 300 */     this.jLabel13.setText("Password:");
/*     */ 
/* 302 */     GroupLayout jPanel6Layout = new GroupLayout(this.jPanel6);
/* 303 */     this.jPanel6.setLayout(jPanel6Layout);
/* 304 */     jPanel6Layout.setHorizontalGroup(
/* 305 */       jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 306 */       .addGroup(jPanel6Layout.createSequentialGroup()
/* 307 */       .addContainerGap()
/* 308 */       .addComponent(this.jLabel10)
/* 309 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 310 */       .addComponent(this.proxyAddressTextField)
/* 311 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 312 */       .addComponent(this.jLabel11)
/* 313 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 314 */       .addComponent(this.proxyPortTextField, -2, 39, -2)
/* 315 */       .addGap(18, 18, 18)
/* 316 */       .addComponent(this.jLabel12)
/* 317 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 318 */       .addComponent(this.proxyUsernameTextField, -2, 64, -2)
/* 319 */       .addGap(18, 18, 18)
/* 320 */       .addComponent(this.jLabel13)
/* 321 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 322 */       .addComponent(this.proxyPasswordTextField, -2, 64, -2)
/* 323 */       .addContainerGap()));
/*     */ 
/* 325 */     jPanel6Layout.setVerticalGroup(
/* 326 */       jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 327 */       .addGroup(jPanel6Layout.createSequentialGroup()
/* 328 */       .addContainerGap()
/* 329 */       .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 330 */       .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 331 */       .addComponent(this.proxyUsernameTextField, -2, -1, -2)
/* 332 */       .addComponent(this.jLabel12))
/* 333 */       .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 334 */       .addComponent(this.jLabel11)
/* 335 */       .addComponent(this.proxyPortTextField, -2, -1, -2))
/* 336 */       .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 337 */       .addComponent(this.jLabel10)
/* 338 */       .addComponent(this.proxyAddressTextField, -2, -1, -2)
/* 339 */       .addComponent(this.jLabel13)
/* 340 */       .addComponent(this.proxyPasswordTextField, -2, -1, -2)))
/* 341 */       .addContainerGap(-1, 32767)));
/*     */ 
/* 344 */     GroupLayout layout = new GroupLayout(this);
/* 345 */     setLayout(layout);
/* 346 */     layout.setHorizontalGroup(
/* 347 */       layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 348 */       .addGroup(layout.createSequentialGroup()
/* 349 */       .addContainerGap()
/* 350 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 351 */       .addComponent(this.jPanel3, -1, -1, 32767)
/* 352 */       .addComponent(this.jPanel5, -1, -1, 32767)
/* 353 */       .addGroup(layout.createSequentialGroup()
/* 354 */       .addComponent(this.jPanel1, -1, -1, 32767)
/* 355 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 356 */       .addComponent(this.jPanel2, -1, -1, 32767))
/* 357 */       .addComponent(this.jPanel6, -1, -1, 32767))
/* 358 */       .addContainerGap()));
/*     */ 
/* 360 */     layout.setVerticalGroup(
/* 361 */       layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 362 */       .addGroup(layout.createSequentialGroup()
/* 363 */       .addContainerGap()
/* 364 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 365 */       .addComponent(this.jPanel2, -2, -1, -2)
/* 366 */       .addComponent(this.jPanel1, -2, -1, -2))
/* 367 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 368 */       .addComponent(this.jPanel3, -1, -1, 32767)
/* 369 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 370 */       .addComponent(this.jPanel5, -2, -1, -2)
/* 371 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 372 */       .addComponent(this.jPanel6, -2, -1, -2)
/* 373 */       .addContainerGap()));
/*     */   }
/*     */ 
/*     */   public void initFields()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void setCloseConncectionPattern(String closeConncectionPattern)
/*     */   {
/* 426 */     this.closeConncectionPatternTextField.setText(closeConncectionPattern);
/*     */   }
/*     */ 
/*     */   public String getCloseConncectionPattern() {
/* 430 */     return this.closeConncectionPatternTextField.getText();
/*     */   }
/*     */ 
/*     */   public void setConnectionId(String connectionId) {
/* 434 */     this.connectionIdTextField.setText(connectionId);
/*     */   }
/*     */ 
/*     */   public String getConnectionId() {
/* 438 */     return this.connectionIdTextField.getText();
/*     */   }
/*     */ 
/*     */   public void setContentEncoding(String contentEncoding) {
/* 442 */     this.contentEncodingTextField.setText(contentEncoding);
/*     */   }
/*     */ 
/*     */   public String getContentEncoding() {
/* 446 */     return this.contentEncodingTextField.getText();
/*     */   }
/*     */ 
/*     */   public void setContextPath(String contextPath) {
/* 450 */     this.contextPathTextField.setText(contextPath);
/*     */   }
/*     */ 
/*     */   public String getContextPath() {
/* 454 */     return this.contextPathTextField.getText();
/*     */   }
/*     */ 
/*     */   public void setProtocol(String protocol) {
/* 458 */     this.protocolTextField.setText(protocol);
/*     */   }
/*     */ 
/*     */   public String getProtocol() {
/* 462 */     return this.protocolTextField.getText();
/*     */   }
/*     */ 
/*     */   public void setProxyAddress(String proxyAddress) {
/* 466 */     this.proxyAddressTextField.setText(proxyAddress);
/*     */   }
/*     */ 
/*     */   public String getProxyAddress() {
/* 470 */     return this.proxyAddressTextField.getText();
/*     */   }
/*     */ 
/*     */   public void setProxyPassword(String proxyPassword) {
/* 474 */     this.proxyPasswordTextField.setText(proxyPassword);
/*     */   }
/*     */ 
/*     */   public String getProxyPassword() {
/* 478 */     return this.proxyPasswordTextField.getText();
/*     */   }
/*     */ 
/*     */   public void setProxyPort(String proxyPort) {
/* 482 */     this.proxyPortTextField.setText(proxyPort);
/*     */   }
/*     */ 
/*     */   public String getProxyPort() {
/* 486 */     return this.proxyPortTextField.getText();
/*     */   }
/*     */ 
/*     */   public void setProxyUsername(String proxyUsername) {
/* 490 */     this.proxyUsernameTextField.setText(proxyUsername);
/*     */   }
/*     */ 
/*     */   public String getProxyUsername() {
/* 494 */     return this.proxyUsernameTextField.getText();
/*     */   }
/*     */ 
/*     */   public void setResponsePattern(String responsePattern) {
/* 498 */     this.responsePatternTextField.setText(responsePattern);
/*     */   }
/*     */ 
/*     */   public String getResponsePattern() {
/* 502 */     return this.responsePatternTextField.getText();
/*     */   }
/*     */ 
/*     */   public void setResponseTimeout(String responseTimeout) {
/* 506 */     this.responseTimeoutTextField.setText(responseTimeout);
/*     */   }
/*     */ 
/*     */   public String getResponseTimeout() {
/* 510 */     return this.responseTimeoutTextField.getText();
/*     */   }
/*     */ 
/*     */   public void setConnectionTimeout(String connectionTimeout) {
/* 514 */     this.connectionTimeoutTextField.setText(connectionTimeout);
/*     */   }
/*     */ 
/*     */   public String getConnectionTimeout() {
/* 518 */     return this.connectionTimeoutTextField.getText();
/*     */   }
/*     */ 
/*     */   public void setServerAddress(String serverAddress) {
/* 522 */     this.serverAddressTextField.setText(serverAddress);
/*     */   }
/*     */ 
/*     */   public String getServerAddress() {
/* 526 */     return this.serverAddressTextField.getText();
/*     */   }
/*     */ 
/*     */   public void setServerPort(String serverPort) {
/* 530 */     this.serverPortTextField.setText(serverPort);
/*     */   }
/*     */ 
/*     */   public String getServerPort() {
/* 534 */     return this.serverPortTextField.getText();
/*     */   }
/*     */ 
/*     */   public void setRequestPayload(String requestPayload) {
/* 538 */     this.requestPayloadEditorPane.setText(requestPayload);
/*     */   }
/*     */ 
/*     */   public String getRequestPayload() {
/* 542 */     return this.requestPayloadEditorPane.getText();
/*     */   }
/*     */ 
/*     */   public void setStreamingConnection(Boolean streamingConnection) {
/* 546 */     this.streamingConnectionCheckBox.setSelected(streamingConnection.booleanValue());
/*     */   }
/*     */ 
/*     */   public Boolean isStreamingConnection() {
/* 550 */     return Boolean.valueOf(this.streamingConnectionCheckBox.isSelected());
/*     */   }
/*     */ 
/*     */   public void setIgnoreSslErrors(Boolean ignoreSslErrors) {
/* 554 */     this.ignoreSslErrorsCheckBox.setSelected(ignoreSslErrors.booleanValue());
/*     */   }
/*     */ 
/*     */   public Boolean isIgnoreSslErrors() {
/* 558 */     return Boolean.valueOf(this.ignoreSslErrorsCheckBox.isSelected());
/*     */   }
/*     */ 
/*     */   public void setImplementation(String implementation) {
/* 562 */     this.implementationComboBox.setSelectedItem(implementation);
/*     */   }
/*     */ 
/*     */   public String getImplementation() {
/* 566 */     return (String)this.implementationComboBox.getSelectedItem();
/*     */   }
/*     */ 
/*     */   public void setMessageBacklog(String messageBacklog) {
/* 570 */     this.messageBacklogTextField.setText(messageBacklog);
/*     */   }
/*     */ 
/*     */   public String getMessageBacklog() {
/* 574 */     return this.messageBacklogTextField.getText();
/*     */   }
/*     */ 
/*     */   public ArgumentsPanel getAttributePanel()
/*     */   {
/* 581 */     return this.attributePanel;
/*     */   }
/*     */ }

