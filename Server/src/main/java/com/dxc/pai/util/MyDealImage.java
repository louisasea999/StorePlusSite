package com.dxc.pai.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;

public class MyDealImage {

	
	//验证码
	public static int isBlack(int colorInt) {  
        Color color = new Color(colorInt);  
        if (color.getRed() + color.getGreen() + color.getBlue() <= 600) {  
            return 1;  
        }  
        return 0;  
    }  
  
	//背景色
    public static int isWhite(int colorInt) {  
        Color color = new Color(colorInt);  
        if (color.getRed() + color.getGreen() + color.getBlue() > 600) {  
            return 1;  
        }  
        return 0;  
    }  
  
    //扫描验证码所有的像素颜色过滤掉不要的颜色  
    public static BufferedImage removeBackgroud4Tone(String picFile)  
            throws Exception {  
        BufferedImage img = ImageIO.read(new File(picFile));  
        int width = img.getWidth();  
        int height = img.getHeight();  
        for (int x = 0; x < width; ++x) {  
            for (int y = 0; y < height; ++y) {  
                if (isWhite(img.getRGB(x, y)) == 1) {  
                    img.setRGB(x, y, Color.WHITE.getRGB());  
                } else {  
                    img.setRGB(x, y, Color.BLACK.getRGB());  
                }  
            }  
        }  
        return img;  
    }  
    
    
    public static void removeOther(BufferedImage img)
    {
    	int width = img.getWidth();  
        int height = img.getHeight();  
        
        for (int y = 0; y < height; ++y) 
        {
        	for (int x = 0; x < width; ++x) {
        		if(x+2<width && x-2>0)
        		{
        			if(isBlack(img.getRGB(x, y))== 1 )
            		{
            			if(isBlack(img.getRGB(x+1, y))==1 && isWhite(img.getRGB(x+2, y))== 1 && isWhite(img.getRGB(x-1, y))== 1)
            				img.setRGB(x, y, Color.WHITE.getRGB()); 
            			if(isBlack(img.getRGB(x-1, y))==1 && isWhite(img.getRGB(x-2, y))== 1 && isWhite(img.getRGB(x+1, y))== 1)
            				img.setRGB(x, y, Color.WHITE.getRGB());  
            			if(isWhite(img.getRGB(x-1, y))== 1 && isWhite(img.getRGB(x+1, y))== 1)
            				img.setRGB(x, y, Color.WHITE.getRGB()); 
            		}	      
        		}
        	}
        }
        
        for (int x = 0; x < width; ++x) {  
            for (int y = 0; y < height; ++y) { 
            	
            	
            	if(y+2<height && y-2>0)
            	{
            		if(isBlack(img.getRGB(x, y))== 1 )
            		{
            			if(isBlack(img.getRGB(x, y+1))==1 && isWhite(img.getRGB(x, y+2))== 1 && isWhite(img.getRGB(x, y-1))== 1)
            				img.setRGB(x, y, Color.WHITE.getRGB()); 
            			if(isBlack(img.getRGB(x, y-1))==1 && isWhite(img.getRGB(x, y-2))== 1 && isWhite(img.getRGB(x, y+1))== 1)
            				img.setRGB(x, y, Color.WHITE.getRGB());  
            			if(isWhite(img.getRGB(x, y-1))== 1 && isWhite(img.getRGB(x, y+1))== 1)
            				img.setRGB(x, y, Color.WHITE.getRGB()); 
            		}	            	
            	}
            }
           }
        
        for (int y = 0; y < height; ++y) 
        {
        	for (int x = 0; x < width; ++x) {
        		if(x+2<width && x-2>0)
        		{
        			if(isBlack(img.getRGB(x, y))== 1 )
            		{
            			if(isBlack(img.getRGB(x+1, y))==1 && isWhite(img.getRGB(x+2, y))== 1 && isWhite(img.getRGB(x-1, y))== 1)
            				img.setRGB(x, y, Color.WHITE.getRGB()); 
            			if(isBlack(img.getRGB(x-1, y))==1 && isWhite(img.getRGB(x-2, y))== 1 && isWhite(img.getRGB(x+1, y))== 1)
            				img.setRGB(x, y, Color.WHITE.getRGB());  
            			if(isWhite(img.getRGB(x-1, y))== 1 && isWhite(img.getRGB(x+1, y))== 1)
            				img.setRGB(x, y, Color.WHITE.getRGB()); 
            		}	      
        		}
        	}
        }
       
        
        try {
			ImageIO.write(img, "JPG", new File("C://Users/temp/IdentifyImage.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    }
 
    public static String identifyCode() {  
    	try {
    		BufferedImage img = removeBackgroud4Tone("C://Users/temp/codeImage.jpg");
			removeOther(img);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
        Tesseract1 instance = new Tesseract1();  
        File imageFile = new File("C://Users/temp/IdentifyImage.jpg"); // instance.setLanguage("chi_sim");  
        String result = null;  
        try {  
            result = instance.doOCR(imageFile);  
        } catch (TesseractException e) { // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        if(result.length()>4)
        {
        	return result.substring(0, 4);  
        }
        else
        {
        	return result;
        }
  
        
    }  
}
