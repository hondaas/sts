package com.melon.music.vo;

import com.melon.common.web.pager.Pager;
import com.melon.common.web.pager.PagerFactory;

public class MusicSearchVO {

	private Pager pager;

	private String albumId;
	private String artistId;
	
	public String getAlbumId() {
		return albumId;
	}

	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}

	public String getArtistId() {
		return artistId;
	}

	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}

	public Pager getPager() {
		if(pager == null)
		{pager=new PagerFactory().getPager(Pager.ORACLE, 20, 10);
		//한페이지 20개 게시물, 페이지는 10개씩
		
		}
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
	
	
	
}
