package com.wolf.utils;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;
import java.util.UUID;


/**
 * 提供针对文件读写的简化操作。
 */
public class IOUtils
{
	/**
	 * 使用UUID策略生成新的文件名，保留原有的扩展名。
	 * 
	 * @param fileName
	 *        原文件名
	 * @return 使用UUID策略生成新的文件名
	 */
	public static String renameFile( String fileName )
	{
		String suffixName = fileName.substring( fileName.lastIndexOf( "." ) + 1 , fileName.length() );
		String newFileName = UUID.randomUUID().toString();
		if ( StringUtils.isNotBlank( suffixName ) )
			newFileName = UUID.randomUUID() + "." + suffixName;

		return newFileName;
	}

	/**
	 * 上传指定的文件，如果上传失败，会抛出RuntimeException
	 * 
	 * @param uploadFile
	 *        要上传的文件
	 * @param path
	 *        上传文件的完整路径，不包含文件名，如C:\temp
	 * @param uploadFileName
	 *        上传文件的文件名
	 */
	public static void uploadFile( File uploadFile , String path , String uploadFileName )
	{
		// 处理路径中的斜杠
		path = path.replaceAll( "/" , "\\\\" );

		// 创建文件目录
		File dir = new File( path );
		if ( !dir.exists() )
		{
			dir.mkdirs();
		}

		try (FileOutputStream fos = new FileOutputStream( path + "\\" + uploadFileName ); FileInputStream fis = new FileInputStream( uploadFile ))
		{
			// 上传文件
			byte[] buffer = new byte[4096];
			int len = 0;
			while ( ( len = fis.read( buffer ) ) > 0 )
			{
				fos.write( buffer , 0 , len );
			}
		}
		catch ( Exception e )
		{
			// 如果发生异常，则删除文件
			File f = new File( path + "\\" + uploadFileName );
			if ( f.exists() )
			{
				f.delete();
			}
			throw new RuntimeException( "Upload file to path '" + path + "\\" + uploadFileName + "' failed：" + e );
		}
	}

	/**
	 * 先删除一个旧的文件，再上传指定的文件
	 * 
	 * @param upload
	 *        要上传的文件
	 * @param path
	 *        上传文件的完整路径，不包含文件名，如C:\temp
	 * @param uploadFileName
	 *        上传文件的文件名
	 * @param oldFilePath
	 *        要删除的旧文件完整路径，包含文件名，如C:\temp\test.txt
	 */
	public static void uploadFile( File upload , String path , String uploadFileName , String oldFilePath )
	{
		uploadFile( upload , path , uploadFileName );

		if ( StringUtils.isNotBlank( oldFilePath ) )
		{
			File file = new File( oldFilePath );

			if ( file.exists() )
			{
				file.delete();
			}
		}
	}

	/**
	 * 删除指定的文件，删除失败时，会抛出RuntimeException。
	 * 
	 * @param path
	 *        要删除文件的绝对路径，如C:\temp\test.txt
	 */
	public static void deleteFile( String path )
	{
		File file = new File( path );
		if ( file.exists() )
		{
			boolean isDeleted = file.delete();
			if ( !isDeleted )
			{
				throw new RuntimeException( "File in '" + path + "' delete failed" );
			}
		}
	}

	/**
	 * 读取classpath下的properties配置文件。
	 * 
	 * @param filename
	 *        文件名
	 * @return 读取到的文件，如果发生异常，返回null
	 */
	public static Properties readProperties( String filename )
	{
		String classPath = "";
		try
		{
			URL url = Thread.currentThread().getContextClassLoader().getResource( "" );
			classPath = url.getPath();
			classPath = URLDecoder.decode( classPath , "UTF-8" );
		}
		catch ( UnsupportedEncodingException e1 )
		{
			return null;
		}

		Properties props = new Properties();

		try (InputStream in = new FileInputStream( classPath + filename ))
		{
			props.load( in );
			return props;
		}
		catch ( IOException e )
		{
			return null;
		}
	}

	/**
	 * 以字节数组的方式读取文件。
	 * 
	 * @param file
	 *        要读取的文件
	 * @return 读取后的字节数组，如果发生异常，返回null
	 */
	public static byte[] readFile( File file )
	{
		try (FileInputStream fileInputStream = new FileInputStream( file ); ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream())
		{
			byte[] bytes = new byte[1024];
			int len = 0;
			while ( ( len = fileInputStream.read( bytes ) ) != -1 )
			{
				byteArrayOutputStream.write( bytes , 0 , len );
			}
			return byteArrayOutputStream.toByteArray();
		}
		catch ( Exception e )
		{
			return null;
		}
	}

	/**
	 * 以字节数组的方式读取文件。
	 * 
	 * @param path
	 *        要读取的文件完整路径
	 * @return 读取后的字节数组
	 */
	public static byte[] readFile( String path )
	{
		return readFile( new File( path ) );
	}

	/**
	 * 将字节数组写入文件。
	 * 
	 * @param data
	 *        要写入的字节数组
	 * @param file
	 *        写入文件的完整路径
	 */
	public static boolean wirteFile( byte[] data , String file )
	{
		try (FileOutputStream fileOutputStream = new FileOutputStream( new File( file ) ))
		{
			fileOutputStream.write( data );
			fileOutputStream.flush();
			return true;
		}
		catch ( Exception e )
		{
			return false;
		}
	}

	public static String renameFile( File file )
	{
		String fileName = file.getName();
		String suffixName = fileName.substring( fileName.lastIndexOf( "." ) + 1 , fileName.length() );
		String newFileName = UUID.randomUUID().toString();
		if ( StringUtils.isNotBlank( suffixName ) )
			newFileName = UUID.randomUUID() + "." + suffixName;
		file.renameTo( new File(file.getParentFile().getAbsolutePath()+"\\"+newFileName));
		return newFileName;
	}
	public static void main( String[] args )
	{
		File file = new File("D:/图片/1.png");
		renameFile( file );
	}
}
