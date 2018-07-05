
package com.wolf.utils;

/**
 * @author 温海涛 E-mail:wenht3212@gmail.com
 * @version 创建时间：2012-11-16 下午3:26:17
 */
public class JsonResult
{
	private boolean success = false;

	private String message = "";

	private Object o;

	public static JsonResult success( String message )
	{
		JsonResult jr = new JsonResult();
		jr.setSuccess( true );
		jr.setMessage( message );
		return jr;
	}

	public static JsonResult fail( String message )
	{
		JsonResult jr = new JsonResult();
		jr.setSuccess( false );
		jr.setMessage( message );
		return jr;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess( boolean success )
	{
		this.success = success;
	}

	public String getMessage()
	{
		return message;
	}

	public JsonResult setMessage( String message )
	{
		this.message = message;
		return this;
	}

	public Object getO()
	{
		return o;
	}

	public JsonResult setO( Object o )
	{
		this.o = o;
		return this;
	}
}
