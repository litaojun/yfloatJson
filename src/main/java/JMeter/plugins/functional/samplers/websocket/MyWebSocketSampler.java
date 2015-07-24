package JMeter.plugins.functional.samplers.websocket;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.jmeter.protocol.http.util.HTTPConstants;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testelement.TestElement;

public class MyWebSocketSampler extends WebSocketSampler {
    private static final String ARG_VAL_SEP = "="; // $NON-NLS-1$
    private static final String QRY_SEP = "&"; // $NON-NLS-1$
    private static final String WS_PREFIX = "ws://"; // $NON-NLS-1$
    private static final String WSS_PREFIX = "wss://"; // $NON-NLS-1$
    private static final String DEFAULT_PROTOCOL = "ws";
    public URI getUri() throws URISyntaxException {
//        String path = this.getContextPath();
//        // Hack to allow entire URL to be provided in host field
//        if (path.startsWith(WS_PREFIX)
//                || path.startsWith(WSS_PREFIX)) {
//            return new URI(path);
//        }
//        String domain = getServerAddress();
//        String protocol = getProtocol();
//        // HTTP URLs must be absolute, allow file to be relative
//        if (!path.startsWith("/")) { // $NON-NLS-1$
//            path = "/" + path; // $NON-NLS-1$
//        }
//
//        String queryString = getQueryString(getContentEncoding());
//        if (isProtocolDefaultPort()) {
//            return new URI(protocol, null, domain, -1, path, queryString, null);
//        }
        //return new URI(protocol, null, domain, Integer.parseInt(getServerPort()), path, queryString, null);
        return new URI("ws", null, "10.15.144.80", Integer.parseInt(getServerPort()), "/ws", "", null);
    }
    public MyWebSocketSampler()
    {
    	super();
    }

    @Override
    public String getName() {
        return "litaojun";
    }
    public String getContentEncoding() {
        return "UTF-8";
}
    public String getRequestPayload() {
        //return getPropertyAsString("requestPayload");
    	return "";
}
	 public String getServerPort() {
	        return "80";
	    }
	 public String getConnectionTimeout() {
	        //return getPropertyAsString("connectionTimeout", "5000");
		  return "100000";
	    }    
	 public String getResponseTimeout() {
	        return getPropertyAsString("responseTimeout", "200000");
	    }    
	 

		public static void main(String[] args) throws URISyntaxException, IOException, Exception
		{
			// TODO Auto-generated method stub
			MyWebSocketSampler myweb = new MyWebSocketSampler();
			//myweb.send("/quote/dyna?obj=SH600000&output=json&sub=1");
			myweb.send("/quote/dyna?obj=SH601519&sub=1");
			SampleResult srt = myweb.sample(null);
			String tmpstr = srt.getResponseDataAsString();
			System.out.println("tmpstr----"+tmpstr + "----end");

		}
}
