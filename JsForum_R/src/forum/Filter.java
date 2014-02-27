package forum;

public class Filter {


	public static String filterAll(String string)
    {
		string = filterHtmlTag( string );
		string = filterBreaks( string );
		string = filterTextStyle( string );
		string = filterImage( string );
		string = filterURL( string );
		string = filterEmoticons( string );
		return string;
	}
	
	public static String filterHtmlTag(String string)
    {
        if(string == null || string.length() < 1)
        {
            return string;
        }
        
        StringBuffer source = new StringBuffer(string);
        
        for(int i = 0; i < source.length(); i++)
        {
            char c = source.charAt(i);
            if(c == '>')
            {
                source.replace(i,i+1,"&gt;");
            } 
            else if(c == '<')
            {
                source.replace(i,i+1,"&lt;");
            } 
            else if(c == '&')
            {
                source.replace(i,i+1,"&#38;");
            }
        }

        
        return source.toString();
    }

   	public static String filterBreaks(String string)
    {
        if(string == null || string.length() < 1)
        {
            return string;
        }
        
        StringBuffer source = new StringBuffer(string);
        
        for(int i = 0; i < source.length(); i++)
        {
            if(source.charAt(i) == '\n')
            {
                source.replace(i,i+1,"<br>"); 
            } 
		}
        
        return source.toString();
    }

	public static String filterTextStyle(String string)
	{
        if(string == null || string.length() < 1)
        {
            return string;
        }

        StringBuffer source = new StringBuffer(string);
        
        char charArray[] = {'B','b','I','i','U','u','S','s'};
        
        for(int i = 0; i < source.length(); i++)
        {
            if(source.charAt(i) == '[' && source.charAt(i+2) == ']')
            {
                for(int j = 0; j < charArray.length; j++)
            	{
	                if(source.charAt(i+1) == charArray[j])
	                {
	                	source.replace(i,i+3,"<"+ charArray[j] +">"); 
	                }
	            }
            }
            else if(source.charAt(i) == '[' &&  source.charAt(i+1) == '/' && source.charAt(i+3) == ']')
            {
            	for(int j = 0; j < charArray.length; j++)
            	{
	            	if(source.charAt(i+2) == charArray[j])
	            	{
	                	source.replace(i,i+4,"</"+ charArray[j] +">"); 
	                }
	            }
            }
		}
		return  source.toString();
	}
	
	public static String filterImage(String string)
	{
        if(string == null || string.length() < 1)
        {
            return string;
        }
        
		StringBuffer source = new StringBuffer( string );
    
        for(int i = 0; i < source.length(); i++)
        {
            if(source.charAt(i) == '[')
            {
            	for( int j = i+1;j < (source.length());j++)
            	{
            		if(source.charAt(j) == ']')
            		{
            			String subString = source.substring(i+1,j);
						if(subString.equals("IMG") || subString.equals("img"))
						{
							source.replace(i,j+1,"<img src=");
						}else if(subString.equals("/IMG") || subString.equals("/img"))
						{
							source.replace(i,j+1,">");	
						} 
            		}	
            	}

            }
        }


		return  source.toString();
	}

	public static String filterURL(String string)
	{
        if(string == null || string.length() < 1)
        {
            return string;
        }
        
		StringBuffer source = new StringBuffer( string );
    
        for(int i = 0; i < source.length(); i++)
        {
            if(source.charAt(i) == '[')
            {
            	for( int j = i+1;j < (source.length());j++)
            	{
            		if(source.charAt(j) == ']')
            		{
            			String subString = source.substring(i+1,i+5);
						if(subString.equals("URL=") || subString.equals("url="))
						{
							source.replace(i,5,"<a href=");
							source.replace(j+3,j+4," target=new>");
						}else if(subString.equals("/URL") || subString.equals("/URL"))
						{
							source.replace(i,j+1,"</a>");	
						} 
            		}	
            	}

            }
        }
		return  source.toString();
	}
	
    public static String filterEmoticons(String string)
    {
        StringBuffer buffer = new StringBuffer( string );
    
        for(int i = 0; i < buffer.length(); i++){          
         	if(buffer.charAt(i) == ':'){
        		for( int j = i+1;j < (buffer.length());j++){        			
        			if(buffer.charAt(j) == ':'){
        				String subString = buffer.substring(i+1,j);
						if(subString.equals("mellow")){
							buffer.replace(i,j+1,"<img src=./emoticons/mellow.gif border=0>");
						}else if(subString.equals("huh")){
							buffer.replace(i,j+1,"<img src=./emoticons/huh.gif border=0>");
						}else if(subString.equals("rolleyes")){
							buffer.replace(i,j+1,"<img src=./emoticons/rolleyes.gif border=0>");
						}else if(subString.equals("lol")){
							buffer.replace(i,j+1,"<img src=./emoticons/lol.gif border=0>");
						}else if(subString.equals("wub")){
							buffer.replace(i,j+1,"<img src=./emoticons/wub.gif border=0>");
						}else if(subString.equals("angry")){
							buffer.replace(i,j+1,"<img src=./emoticons/angry.gif border=0>");
						}else if(subString.equals("unsure")){
							buffer.replace(i,j+1,"<img src=./emoticons/unsure.gif border=0>");
						}else if(subString.equals("wacko")){
							buffer.replace(i,j+1,"<img src=./emoticons/wacko.gif border=0>");
						}else if(subString.equals("blink")){
							buffer.replace(i,j+1,"<img src=./emoticons/blink.gif border=0>");
						}else if(subString.equals("ph34r")){
							buffer.replace(i,j+1,"<img src=./emoticons/ph34r.gif border=0>");
						}else if(subString.equals("bounce")){
							buffer.replace(i,j+1,"<img src=./emoticons/bounce.gif border=0>");
						}else if(subString.equals("fuck")){
							buffer.replace(i,j+1,"<img src=./emoticons/fuck.gif border=0>");
						}else if(subString.equals("scream")){
							buffer.replace(i,j+1,"<img src=./emoticons/scream.gif border=0>");
						}else if(subString.equals("silly")){
							buffer.replace(i,j+1,"<img src=./emoticons/silly.gif border=0>");
						}else if(subString.equals("frusty")){
							buffer.replace(i,j+1,"<img src=./emoticons/frusty.gif border=0>");
						}else if(subString.equals("kwijl")){
							buffer.replace(i,j+1,"<img src=./emoticons/kwijl.gif border=0>");
						}else if(subString.equals("puke")){
							buffer.replace(i,j+1,"<img src=./emoticons/puke.gif border=0>");
						}else if(subString.equals("bid")){
							buffer.replace(i,j+1,"<img src=./emoticons/bid.gif border=0>");
						}else if(subString.equals("hammer")){
							buffer.replace(i,j+1,"<img src=./emoticons/hammer.gif border=0>");
						}else if(subString.equals("good")){
							buffer.replace(i,j+1,"<img src=./emoticons/good.gif border=0>");														
        				}
        			}
        		}
			}   	
    	}
    	return buffer.toString();
    }
    

	
}