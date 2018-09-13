package com.xter.slimnews.presenstation.component.app;

import com.facebook.stetho.Stetho;
import com.xter.support.component.BaseApp;
import com.xter.support.util.L;

/**
 * Created by XTER on 2018/9/10.
 * 应用入口
 */

public class SlimApp extends BaseApp {
	@Override
	protected void init() {
		L.DEBUG = true;

		Stetho.initializeWithDefaults(this);
	}
}
