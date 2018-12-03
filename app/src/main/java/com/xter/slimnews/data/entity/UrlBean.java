package com.xter.slimnews.data.entity;

/**
 * Created by XTER on 2018/9/18.
 */

public class UrlBean {
	/**
	 * height : 426
	 * width : 640
	 * url : http://img1.gtimg.com/sports/pics/hv1/198/144/2098/136459368.jpg
	 */

	public int height;
	public int width;
	public String url;

	public UrlBean(int height, int width, String url) {
		this.height = height;
		this.width = width;
		this.url = url;
	}

	@Override
	public String toString() {
		return "UrlBean{" +
				"height=" + height +
				", width=" + width +
				", url='" + url + '\'' +
				'}';
	}
}
