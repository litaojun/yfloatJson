package com.gw.dzhyun.util;

public class YfloatObj {
	public double value;
	public int dp; 
	public String error;
	
	public YfloatObj()
	{
		
	}
	public void set(double value,int dp,String msg)
	{
		this.value=value;
		this.dp=dp;
		this.error=msg;
	}
	
	public double getValue()
	{
		return this.value;
	}
	
	public int getdp()
	{
		return this.dp;
	}
	public String  geterror()
	{
		return this.error;
	}
}
