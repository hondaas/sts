package com.melon.music.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.common.web.DownloadUtil;
import com.melon.music.biz.MusicBiz;
import com.melon.music.biz.MusicBizImpl;
import com.melon.music.vo.MusicVO;

public class ViewMp3downloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MusicBiz musicBiz;

	public ViewMp3downloadServlet() {
		musicBiz = new MusicBizImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String musicId = request.getParameter("musicId");
		
		
		
		
		MusicVO music = musicBiz.getOneMusic(musicId);
		
		
		
		
		String postPath = "C:\\Users\\Admin\\Documents\\sts\\melon\\src\\main\\webapp\\mp3\\";
		
		
		
		
		postPath += music.getAlbumId() + File.separator;
		/*postPath += music.getMp3File();*/
		
		
		
		
		DownloadUtil downloadUtil = DownloadUtil.getInstance(postPath);
		downloadUtil.download(request, response, music.getMp3File(), music.getMp3File());
	
	
	}

}
