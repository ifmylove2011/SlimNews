package com.xter.slimnews.data.entity;

import com.litesuits.orm.db.annotation.Ignore;
import com.litesuits.orm.db.annotation.Table;
import com.xter.slimnews.data.constant.LC;
import com.xter.support.entity.BaseModelAuto;

/**
 * Created by XTER on 2018/9/10.
 * 新闻
 */
@Table(LC.TABLE_NEWS)
public class News extends BaseModelAuto {

	/**
	 * title : 不接受反驳 初秋这5只PVC包袋最时髦
	 * time : 2018-09-03 10:54:00
	 * src : 新浪时尚
	 * category : fashion
	 * pic : https://n.sinaimg.cn/fashion/crawl/202/w550h452/20180903/bFXB-hiqtcan2083271.jpg
	 * content : 导语：今年买包我只买PVC的！你呢？！（来源：时尚cosmo）</span>CHANEL </strong>CHANEL2018春夏秀场中，各种拼色的PVC包包特别抢眼！之后，CHANEL这股PVC包包热潮就一直流行到现在！前段时间欧阳娜娜为CHANEL拍摄的一组照片中，就背了一直小号的PVC拼接包包，特别青春！之前她去看CHANEL2018春夏秀的机场出发照，也背了这款包包，虽然穿的是比较简单的一身牛仔，但却十分清爽甜美。表姐刘雯拿过的这款是大号的，感觉更加实用，超级能装啊！ 明星博主拿的比较多的是这款Mini size的，因为颜色拼接的非常靓丽，所以十分适合夏天的调调。另外一个颜色COCO觉得更萌，是更清爽的樱花粉和薄荷绿拼色：大号配饰也不输小号，而且能装实用度其实更高。 这只大的也特别上镜。COCO个人觉得秀场上这款宝蓝色超级亮眼哦。 Céline </strong>这款Céline的PVC包包，你敢说它不火？基本上PVC包袋的潮流就是被这只包包带起来的！这个“塑料袋”并不单卖，是和手包、卡包配套销售的，差不多4K的价格。可以买个Céline的卡包和手包，如果你是Céline的忠粉，那么其实也是值的……这款包特别好凹造型啊，因为包袋上一个大大的Céline Logo就足够具有标志性。2018春夏秀场上，模特手拿这只包出来就成为全场的瞩目焦点。PVC潮流也因此再度爆火！就这么随意的攥在手里就很时髦喽。 手提或者背着，博主们喜欢这样做，刚开始COCO都无法接受诶，但现在真心越看越觉得时髦……实物就是下图右边位置的，怎么装，装什么都随意（见下图左边） Prada </strong>越来越年轻的Prada今年也出了PVC水桶包，还是大家抢着买的那种！之前造型师小白在纽约买到这只包的时候，还特意发了条微博晒了一下。里面也有一个内置的零钱手拿包，还蛮大的。（这只是短柄的）这只包包Prada中国官网售价在7800RMB，其实论价格还是不低的。但这种包的整体造型其实更挺括，能装也适合凹造型街拍，官网ins上也分享了这款。Valentino </strong>Valentino2018春夏秀场上，也出现了超好看的PVC包包，延续了经典的Valentino包型。让整个包身变成了纯透明再加点铆钉的点缀，非常时髦前卫。乔欣前段时间在机场Pick了这只，上身效果还蛮好的。 时尚博主 Gala Gonzalez用这只透明的PVC搭配一身驼色，照样高级到不行。 今年4月的时候，Valentino还在北京举办了CANDYSTUD 工场限时概念活动。与此同时推出了新的糖果色PVC包袋，整体感觉更活泼适合夏天。杨幂拿着拍照的就是更俏皮可爱的新款PVC包包，COCO觉得粉粉的再加上彩色的铆钉更具有甜美的夏日少女感！Staud </strong>啊哈！最后一个一定要提的就是COCO想买都没买到的这只Staud！新晋品牌Staud家的这只PVC包包表现实在是太突出，把一堆博主收为死忠粉儿！光是Aimeesong就在Ins上晒了不止一次这只包的街拍。Aimee song同款白色了解一下。 方型的手柄特别复古，而且手拿也十分方便。 Staud本身是个时装品牌，不过因为复古风包包极具年代感的怀旧色彩太漂亮，超级抢手，吸引了不少时装行业人士的关注。而今年最火的莫过于这只PVC方型包包！几千块的价格加上不错的质感，绝对值得入手。还有这么萌的粉色款呢！估计一票少女都要眼冒桃心了！ 特别庆幸的是，这款在我们的Hishop就可以买到哦！
	 * url : http://fashion.sina.cn/s/2018-09-10/detail-ihiqtcan2098209.d.html?vt=4&pos=108
	 * weburl : http://fashion.sina.com.cn/s/tr/2018-09-10/0726/doc-ihiqtcan2098209.shtml
	 */

	public String title;
	public String time;
	public String src;
	public String category;
	public String pic;
	@Ignore
	public String content;
	public String url;
	public String weburl;
	public boolean isRead;

	public News(String title, String time, String src, String category, String pic, String content, String url, String weburl) {
		this.title = title;
		this.time = time;
		this.src = src;
		this.category = category;
		this.pic = pic;
		this.content = content;
		this.url = url;
		this.weburl = weburl;
	}

	@Override
	public String toString() {
		return "News{" +
				"title='" + title + '\'' +
				", time='" + time + '\'' +
				", src='" + src + '\'' +
				", category='" + category + '\'' +
				", pic='" + pic + '\'' +
				", content='" + content + '\'' +
				", url='" + url + '\'' +
				", weburl='" + weburl + '\'' +
				'}';
	}
}
