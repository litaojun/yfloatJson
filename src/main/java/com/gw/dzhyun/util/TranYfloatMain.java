package com.gw.dzhyun.util;

import java.util.Iterator;

import com.alibaba.fastjson.JSONArray;
//import com.gw.dzhyun.util.Yfloat;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class TranYfloatMain {
	public  Yfloat yf = new Yfloat();
	private JSONArray delJsonArr = null;
	JSONObject  delJson = null;
	
	public TranYfloatMain(JSONObject jsonobj,String key)
	{
		this.delJson = jsonobj;
		this.delJsonArr = jsonobj.getJSONObject("Data").getJSONArray(key);
	}
	
	public TranYfloatMain(String jsonstr,String key)
	{
		this.delJson = JSON.parseObject(jsonstr);
		this.delJsonArr = delJson.getJSONObject("Data").getJSONArray(key);
	}
	public TranYfloatMain(String jsonstr)
	{
		this.delJson = JSON.parseObject(jsonstr);
		
		String key = this.getKeyByLength(this.delJson);
		this.delJsonArr = delJson.getJSONObject("Data").getJSONArray(key);
	}
	public String getKeyByLength(JSONObject jsonObj)
	{
		Object[] arr =delJson.getJSONObject("Data").keySet().toArray();
		int num = 0;
		int maxlen = 0;
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i].toString().length()>maxlen)
			{
				num =i;
				maxlen = arr[i].toString().length();
			}
		}
		return arr[num].toString();
	}
	
	
	public void dealNormalJsonObj(Object obj,String key,JSONObject jsb)
	{
		System.out.println("dealNormalJsonObj");
		if(obj instanceof String)
		{
			System.out.println(String.format("key=%s",new String[]{key,obj.toString()}));
		}
		else
		{
			long a = Long.parseLong(obj.toString());
			YfloatObj yoj = this.yf.UnmakeValue(a);
			if(yoj.getdp() == 0)
			{
				System.out.println(String.format("key=%s,and value =%s,hou=%s",new String[]{key,obj.toString(),String.valueOf((long)yoj.getValue())}));
				jsb.put(key, (long)yoj.getValue());
			}
			else
			{
				System.out.println(String.format("key=%s,and value=%s,hou=%s",new String[]{key,obj.toString(),String.valueOf((long)yoj.getValue())}));
				jsb.put(key, yoj.getValue());
			}
		}
	}
	
	public void dealNormalJsonArrObj(Object obj,int num,JSONArray jsbArr,String key)
	{
		
		if(obj instanceof String)
			{
			  System.out.println(String.format("key=%s,and value = %s",new String[]{key,obj.toString()}));
			}
			else
			{
				long a = Long.parseLong(obj.toString());
				YfloatObj yoj = this.yf.UnmakeValue(a);
				//jsbArr.remove(num);
				if(yoj.getdp() == 0)
				{
					System.out.println(String.format("key=%s,and value = %s ,valuehou=%s",new String[]{key,obj.toString(),String.valueOf((long)yoj.getValue())}));
					jsbArr.set(num, (long)yoj.getValue());
				}
				else
				{
					System.out.println(String.format("key=%s,and value = %s  ,valuehou=%s",new String[]{key,obj.toString(),String.valueOf(yoj.getValue())}));
					jsbArr.set(num, yoj.getValue());
				}
			}
	}
  
	public JSONObject dealJsonArray()
	{
		for(int i=0;i<this.delJsonArr.size();i++)
		{
			JSONObject temp = this.delJsonArr.getJSONObject(i);
			this.tranStartJson(temp);
		}
		return this.delJson;
	}
	
	public void tranStartJson(JSONObject jsb)
	{
		Iterator<String> keys=jsb.keySet().iterator();
		while(keys.hasNext())
		{
			String temp = keys.next();
			Object o = jsb.get(temp);
			if(o instanceof JSONObject)
			{
				tranStart((JSONObject)o);
			}
			else
				if(o instanceof JSONArray)
				{
					JSONArray oo = (JSONArray)o;
					for(int i =0 ;i<oo.size();i++)
					{
						Object tm = oo.get(i);
						if(tm instanceof JSONObject)
						{
							tranStart((JSONObject)tm);
						}
						else
							dealNormalJsonArrObj(tm,i,oo,temp);
					}
				}
				else
				{
					dealNormalJsonObj(o,temp,jsb);
				}
		}
		}
	
	
	public void tranStart(JSONObject jsb)
	{
		Iterator<String> keys=jsb.keySet().iterator();
		while(keys.hasNext())
		{
			System.out.println("1111");
			String temp = keys.next();
			System.out.println("1112");
			System.out.println("ss"+temp);
			Object o = jsb.get(temp);
			if(o instanceof JSONObject)
			{
				System.out.println("ss2"+temp);
				tranStart((JSONObject)o);
			}
			else
				if(o instanceof JSONArray)
				{
					System.out.println("ss1"+temp);
					JSONArray oo = (JSONArray)o;
					for(int i =0 ;i<oo.size();i++)
					{
						Object tm = oo.get(i);
						if(tm instanceof JSONObject)
						{
							System.out.println("ss4"+temp);
							tranStart((JSONObject)tm);
						}
						else
							System.out.println("error");
//						else
//						{
//							if(tm instanceof String)
//							{
//								System.out.println(tm);
//							}
//							else
//							{
//								System.out.println("ss5"+temp);
//								long a = Long.parseLong(tm.toString());
//								YfloatObj yoj = this.yf.UnmakeValue(a);
//								oo.remove(i);
//								if(yoj.getdp() == 0)
//								     oo.add(i, (long)yoj.getValue());
//								else
//									oo.add(i, yoj.getValue());
//							}
						//}
					}
				}
				else
				{
					if(o instanceof String)
					{
						System.out.println("0000000000"+o);
					}
					else
					{
						System.out.println("ss7"+temp+o.toString());
						long a = Long.parseLong(o.toString());
						System.out.println("ss7"+temp+o.toString()+"a="+a);
						YfloatObj yoj = this.yf.UnmakeValue(a);
						System.out.println("ss7"+temp+o.toString()+"a="+a+yoj.getValue());
						//jsb.remove(temp);
						//oo.remove(i);
						if(yoj.getdp() == 0)
						{
							System.out.println("ss8");
							jsb.put(temp, (long)yoj.getValue());
							System.out.println("ss9");
						}
						     //oo.add(i, (long)yoj.getValue());
						else
							jsb.put(temp, yoj.getValue());
							//oo.add(i, yoj.getValue());
					}
				}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONObject jsonUAResponse = JSON.parseObject("{\"Qid\":\"\",\"Err\":0,\"Counter\":1,\"Data\":{\"Id\":20,\"RepDataQuoteDynaSingle\":[{\"Obj\":\"SH600128\",\"Data\":{\"Id\":67807630,\"ShiJian\":367723682798,\"ZuiXinJia\":1098,\"KaiPanJia\":898,\"ZuiGaoJia\":1098,\"ZuiDiJia\":898,\"ZuoShou\":998,\"JunJia\":1009,\"ZhangDie\":100,\"ZhangFu\":1002,\"ZhenFu\":2004,\"ChengJiaoLiang\":5470078440,\"XianShou\":655360,\"ChengJiaoE\":5531984989760,\"ZongChengJiaoBiShu\":662359,\"NeiPan\":2283462416,\"WaiPan\":3171608280}}]}}");
//		Iterator<String> keys=jsonUAResponse.keySet().iterator();
//		while(keys.hasNext())
//		{
//			System.out.println(keys.next());
//		}
		System.out.println(jsonUAResponse);
		TranYfloatMain a = new TranYfloatMain(jsonUAResponse,"RepDataQuoteDynaSingle");
		jsonUAResponse = a.dealJsonArray();
		//a.tranStart(jsonUAResponse);
		System.out.println(jsonUAResponse);
		int i = 0;

	}

}
