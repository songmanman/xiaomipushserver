/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-6-7下午5:42:52
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package pxing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-6-7下午5:42:52
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class MArticleJsoupService  {
	public static String TAG = MArticleJsoupService.class.getSimpleName();
	public static List<MArticleBean> parsePXRandTagList(String href, int pageNo) {
		List<MArticleBean> list = new ArrayList<MArticleBean>();
		try {
			// href = makeURL(href, new HashMap<String, Object>() {
			// {
			// }
			// });
		    
			Document doc;
			doc = Jsoup.connect(href)
					.userAgent(UrlUtils.userAgent)
//					.header("Host","www.pximg.com")
					.cookie("Cookie", "UM_distinctid=15d4f9ce60a4eb-050c6be969bcef-35414878-1aeaa0-15d4f9ce60b3bc; CNZZDATA1260136144=1243021942-1500278440-https%253A%252F%252Fwww.baidu.com%252F%7C1501116123; Hm_lvt_21e82dda40c2143d1c3187f1c80935ec=1500279272,1500968826,1501061381; Hm_lpvt_21e82dda40c2143d1c3187f1c80935ec=1501119552")
					.timeout(10000).get();
			System.out.println(TAG+ "url = " + href);

//			Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			try {
				 Element globalnavElement =
				 doc.select("ul.wp-tag-cloud").first();
				Elements moduleElements = globalnavElement.select("li");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
							MArticleBean sbean = new MArticleBean();
							try {
								try {
									Element aElement = moduleElements.get(i).select("a").first();
									if (aElement != null) {
										String hrefa = aElement.attr("href");
										System.out.println(TAG+ "i==" + i + ";hrefa==" + hrefa);
										sbean.setHref(hrefa);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
 

								try {
									Element imgElement = moduleElements.get(i).select("a").first();
									if (imgElement != null) {
										String alt = imgElement.text();
										System.out.println(TAG+ "i==" + i + ";alt==" + alt);
										sbean.setAlt(alt);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
 
							} catch (Exception e) {
								e.printStackTrace();
							}

							list.add(sbean);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<MArticleBean> parseList(String href, int pageNo) {
		List<MArticleBean> list = new ArrayList<MArticleBean>();
		try {
			// href = makeURL(href, new HashMap<String, Object>() {
			// {
			// }
			// });
		    
			Document doc;
			doc = Jsoup.connect(href)
					.userAgent(UrlUtils.userAgent)
//					.header("Host","www.pximg.com")
					.cookie("Cookie", "UM_distinctid=15d4f9ce60a4eb-050c6be969bcef-35414878-1aeaa0-15d4f9ce60b3bc; CNZZDATA1260136144=1243021942-1500278440-https%253A%252F%252Fwww.baidu.com%252F%7C1501116123; Hm_lvt_21e82dda40c2143d1c3187f1c80935ec=1500279272,1500968826,1501061381; Hm_lpvt_21e82dda40c2143d1c3187f1c80935ec=1501119552")
					.timeout(10000).get();
			System.out.println(TAG+ "url = " + href);

//			Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			try {
				 Element globalnavElement =
				 doc.select("ul.list-cat").first();
				Elements moduleElements = globalnavElement.select("li");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
							MArticleBean sbean = new MArticleBean();
							try {
								try {
									Element aElement = moduleElements.get(i).select("a").first();
									if (aElement != null) {
										String hrefa = aElement.attr("href");
										System.out.println(TAG+ "i==" + i + ";hrefa==" + hrefa);
										sbean.setHref(hrefa);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("img").first();
									if (imgElement != null) {
										String src = "https:"+imgElement.attr("lazysrc");
										System.out.println(TAG+ "i==" + i + ";src==" + src);
										sbean.setSrc(src);

									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("img").first();
									if (imgElement != null) {
										String alt = imgElement.attr("alt");
										if(alt.contains("%u")){
											alt = EscapeUnescapeUtils.unescape(alt);
										}
										System.out.println(TAG+ "i==" + i + ";alt==" + alt);
										sbean.setAlt(alt);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("span.text").first();
									if (imgElement != null) {
										String meta = imgElement.text();
										System.out.println(TAG+ "i==" + i + ";meta==" + meta);
										sbean.setMeta(meta);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
 
							} catch (Exception e) {
								e.printStackTrace();
							}

							list.add(sbean);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<MArticleBean> parsePXMImageFootList(String href, int pageNo) {
		List<MArticleBean> list = new ArrayList<MArticleBean>();
		try {
			// href = makeURL(href, new HashMap<String, Object>() {
			// {
			// }
			// });
		    
			Document doc;
			doc = Jsoup.connect(href)
					.userAgent(UrlUtils.userAgent)
//					.header("Host","www.pximg.com")
					.cookie("Cookie", "UM_distinctid=15d4f9ce60a4eb-050c6be969bcef-35414878-1aeaa0-15d4f9ce60b3bc; CNZZDATA1260136144=1243021942-1500278440-https%253A%252F%252Fwww.baidu.com%252F%7C1501116123; Hm_lvt_21e82dda40c2143d1c3187f1c80935ec=1500279272,1500968826,1501061381; Hm_lpvt_21e82dda40c2143d1c3187f1c80935ec=1501119552")
					.timeout(10000).get();
			System.out.println(TAG+ "url = " + href);

//			Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			try {
				 Element globalnavElement = doc.select("div.more").first();
				Elements moduleElements = globalnavElement.select("a");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
							MArticleBean sbean = new MArticleBean();
							try {
								try {
									Element aElement = moduleElements.get(i).select("a").first();
									if (aElement != null) {
										String hrefa = aElement.attr("href");
										System.out.println(TAG+ "i==" + i + ";hrefa==" + hrefa);
										sbean.setHref(hrefa);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("img").first();
									if (imgElement != null) {
										String src = "https:"+imgElement.attr("lazysrc");
										System.out.println(TAG+ "i==" + i + ";src==" + src);
										sbean.setSrc(src);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("img").first();
									if (imgElement != null) {
										String alt = imgElement.attr("alt");
										if(alt.contains("%u")){
											alt = EscapeUnescapeUtils.unescape(alt);
										}
										System.out.println(TAG+ "i==" + i + ";alt==" + alt);
										sbean.setAlt(alt);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("span.text").first();
									if (imgElement != null) {
										String meta = imgElement.text();
										System.out.println(TAG+ "i==" + i + ";meta==" + meta);
										sbean.setMeta(meta);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
 
							} catch (Exception e) {
								e.printStackTrace();
							}

							list.add(sbean);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<MArticleBean> parseSexNovelList(String href, int pageNo) {
		List<MArticleBean> list = new ArrayList<MArticleBean>();
		try {
			// href = makeURL(href, new HashMap<String, Object>() {
			// {
			// }
			// });
		    
			Document doc;
			doc = Jsoup.connect(href)
					.userAgent(UrlUtils.userAgent)
//					.header("Host","www.pximg.com")
					.cookie("Cookie", "UM_distinctid=15d4f9ce60a4eb-050c6be969bcef-35414878-1aeaa0-15d4f9ce60b3bc; CNZZDATA1260136144=1243021942-1500278440-https%253A%252F%252Fwww.baidu.com%252F%7C1501116123; Hm_lvt_21e82dda40c2143d1c3187f1c80935ec=1500279272,1500968826,1501061381; Hm_lpvt_21e82dda40c2143d1c3187f1c80935ec=1501119552")
					.timeout(10000).get();
			System.out.println(TAG+ "url = " + href);

//			Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			try {
//				 Element globalnavElement =
//				 doc.select("ul.list-cat").first();
				Elements moduleElements = doc.select("div.ui-mod-picsummary");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
							MArticleBean sbean = new MArticleBean();
							try {
								try {
									Element aElement = moduleElements.get(i).select("a").first();
									if (aElement != null) {
										String hrefa = aElement.attr("href");
										System.out.println(TAG+ "i==" + i + ";hrefa==" + hrefa);
										sbean.setHref(hrefa);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("img").first();
									if (imgElement != null) {
										String src = "https:"+imgElement.attr("lazysrc");
										System.out.println(TAG+ "i==" + i + ";src==" + src);
										sbean.setSrc(src);

									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("img").first();
									if (imgElement != null) {
										String alt = imgElement.attr("alt");
										if(alt.contains("%u")){
											alt = EscapeUnescapeUtils.unescape(alt);
										}
										System.out.println(TAG+ "i==" + i + ";alt==" + alt);
										sbean.setAlt(alt);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("div.ui-summary").first();
									if (imgElement != null) {
										String meta = imgElement.text();
										System.out.println(TAG+ "i==" + i + ";meta==" + meta);
										sbean.setMeta(meta);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
 
							} catch (Exception e) {
								e.printStackTrace();
							}

							list.add(sbean);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static List<MArticleBean> parseSearchList(String href, int pageNo) {
		List<MArticleBean> list = new ArrayList<MArticleBean>();
		try {
			// href = makeURL(href, new HashMap<String, Object>() {
			// {
			// }
			// });
			Document doc;
			doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent)
					.cookie("Cookie", "UM_distinctid=15d4f9ce60a4eb-050c6be969bcef-35414878-1aeaa0-15d4f9ce60b3bc; CNZZDATA1260136144=1243021942-1500278440-https%253A%252F%252Fwww.baidu.com%252F%7C1501116123; Hm_lvt_21e82dda40c2143d1c3187f1c80935ec=1500279272,1500968826,1501061381; Hm_lpvt_21e82dda40c2143d1c3187f1c80935ec=1501119552")
					.timeout(10000).get();
			System.out.println(TAG+ "url = " + href);

//			Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			try {
				/**
				 */
				 Element globalnavElement =
				 doc.select("ul.list").first();
				Elements moduleElements = globalnavElement.select("li");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
							MArticleBean sbean = new MArticleBean();
							try {
								try {
									Element aElement = moduleElements.get(i).select("a").first();
									if (aElement != null) {
										String hrefa = aElement.attr("href");
										System.out.println(TAG+ "i==" + i + ";hrefa==" + hrefa);
										sbean.setHref(hrefa);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("img").first();
									if (imgElement != null) {
										String src = "https:"+imgElement.attr("lazysrc");
										System.out.println(TAG+ "i==" + i + ";src==" + src);
										sbean.setSrc(src);

									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("img").first();
									if (imgElement != null) {
										String alt = imgElement.attr("alt");
										if(alt.contains("%u")){
											alt = EscapeUnescapeUtils.unescape(alt);
										}
										System.out.println(TAG+ "i==" + i + ";alt==" + alt);
										sbean.setAlt(alt);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								 
								try {
									Element imgElement = moduleElements.get(i).select("span.text").first();
									if (imgElement != null) {
										String  meta = imgElement.text();
										System.out.println(TAG+ "i==" + i + ";meta==" + meta);
										sbean.setMeta(meta);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

							} catch (Exception e) {
								e.printStackTrace();
							}

							list.add(sbean);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static List<MArticleBean> parsePXMainTopPager(String href, int pageNo) {
		List<MArticleBean> list = new ArrayList<MArticleBean>();
		try {
			// href = makeURL(href, new HashMap<String, Object>() {
			// {
			// }
			// });
			Document doc;
			doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent)
					.cookie("Cookie", "UM_distinctid=15d4f9ce60a4eb-050c6be969bcef-35414878-1aeaa0-15d4f9ce60b3bc; CNZZDATA1260136144=1243021942-1500278440-https%253A%252F%252Fwww.baidu.com%252F%7C1501116123; Hm_lvt_21e82dda40c2143d1c3187f1c80935ec=1500279272,1500968826,1501061381; Hm_lpvt_21e82dda40c2143d1c3187f1c80935ec=1501119552")
					.timeout(10000).get();
			System.out.println(TAG+ "url = " + href);

//			Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			try {
				/**
				 */
				 Element globalnavElement =
				 doc.select("div.swipe").first();
				Elements moduleElements = globalnavElement.select("li");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
							MArticleBean sbean = new MArticleBean();
							try {
								try {
									Element aElement = moduleElements.get(i).select("a").first();
									if (aElement != null) {
										String hrefa = UrlUtils.PXING_COM+aElement.attr("href");
										System.out.println(TAG+ "i==" + i + ";hrefa==" + hrefa);
										sbean.setHref(hrefa);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("img").first();
									if (imgElement != null) {
										String src = "https:"+imgElement.attr("src");
										System.out.println(TAG+ "i==" + i + ";src==" + src);
										sbean.setDataimg(src);

									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("img").first();
									if (imgElement != null) {
										String alt = imgElement.attr("alt");
										if(alt.contains("%u")){
											alt = EscapeUnescapeUtils.unescape(alt);
										}
										System.out.println(TAG+ "i==" + i + ";alt==" + alt);
										sbean.setAlt(alt);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

							} catch (Exception e) {
								e.printStackTrace();
							}

							list.add(sbean);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	

	public static List<MArticleBean> parseImageList(String href, int pageNo) {
		List<MArticleBean> list = new ArrayList<MArticleBean>();
		try {
			// href = makeURL(href, new HashMap<String, Object>() {
			// {
			// }
			// });
			if(!href.contains("_")){
				if(pageNo>1){
					//http://m.mm131.com/xinggan/2847.html
					//http://m.mm131.com/xinggan/2847_2.html
					href = href.replace(".html", "_")+pageNo+".html";
				}
			}
			
			Document doc;
			doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			System.out.println(TAG+ "url = " + href);

//			Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			try {
				/**
				 * <article class="post">
         <div class="post-header">
            <h2 class="mm-title">大波女神悦悦艺术写真尺度大惑力强</h2>
            <div class="post-data"><span class="post-meta">2017-03-06 21:48:39</span></div>
         </div>
         <div class="post-content single-post-content"><
         a href="2847_2.html"><img alt="大波女神悦悦艺术写真尺度大惑力强(图1)"
          src="http://img2.mm131.com:55888/pic/2847/1.jpg" />
         </a></div>
         
		 <div class="fenye"><a href="2848.html" class="rw">上一篇</a><span class="rw">1/58页</span><a href="2847_2.html">下一张</a></div>
         <div class="mmss"><script type="text/javascript" src="http://img1.mm798.net/mixiu/js/ms.js"></script></div>
         <div class="post-footer single-post-footer">
            <span class="post-meta">分类：<a href="http://m.mm131.com/xinggan/">性感美女</a></span>
            <span class="post-tag">标签：<a href="http://m.mm131.com/tag/%B4%F3%B2%A8">大波</a><a href="http://m.mm131.com/tag/%D4%C3%D4%C3">悦悦</a><a href="http://m.mm131.com/tag/%D2%D5%CA%F5%D0%B4%D5%E6">艺术写真</a></span>
         </div>
		 <div class="jcdog"><script type="text/javascript">show_down();</script></div>
      </article>

				 */
				// Element globalnavElement =
				// doc.select("div.adFocusHtml").first();
				Elements moduleElements = doc.select("article.post");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						Element pElement = moduleElements.get(i);
						 
							MArticleBean sbean = new MArticleBean();
							try {
								try {
									Element aElement = moduleElements.get(i).select("a").first();
									if (aElement != null) {
										String hrefa = aElement.attr("href");
										if(!hrefa.contains(UrlUtils.MM_M)){
											hrefa = href;
										}
										System.out.println(TAG+ "i==" + i + ";hrefa==" + hrefa);
										sbean.setHref(hrefa);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								 

								try {
									Element imgElement = moduleElements.get(i).select("img").first();
									if (imgElement != null) {
										String alt = imgElement.attr("alt");
										if(alt.contains("%u")){
											alt = EscapeUnescapeUtils.unescape(alt);
										}
										System.out.println(TAG+ "i==" + i + ";alt==" + alt);
										sbean.setAlt(alt);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("img").first();
									if (imgElement != null) {
										String dataimg = imgElement.attr("src");
										System.out.println(TAG+ "i==" + i + ";dataimg==" + dataimg);
										sbean.setDataimg(dataimg);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("span.post-meta").first();
									if (imgElement != null) {
										String postmeta = imgElement.text();
										System.out.println(TAG+ "i==" + i + ";postmeta==" + postmeta);
										sbean.setPostmeta(postmeta);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								
								/**
								 * <div class="post-footer single-post-footer">
            <span class="post-meta">分类：<a href="http://m.mm131.com/xinggan/">性感美女</a></span>
            <span class="post-tag">标签：<a href="http://m.mm131.com/tag/%B4%F3%B2%A8">大波</a><a href="http://m.mm131.com/tag/%D4%C3%D4%C3">悦悦</a><a href="http://m.mm131.com/tag/%D2%D5%CA%F5%D0%B4%D5%E6">艺术写真</a></span>
         </div>
								 */
								
								try {
									Element footerElement =  doc.select("div.single-post-footer").first();
									Element metaElement = footerElement.select("span.post-meta").first();
									sbean.setMeta(metaElement.toString());
									
									Element tagElement = footerElement.select("span.post-tag").first();
									sbean.setTag(tagElement.toString());
									
								} catch (Exception e) {
									e.printStackTrace();
								}
								
							} catch (Exception e) {
								e.printStackTrace();
							}

							list.add(sbean);
						}
					 
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static List<MArticleBean> parsePXRandHeadList(String href, int pageNo) {
		List<MArticleBean> list = new ArrayList<MArticleBean>();
		try {
			// href = makeURL(href, new HashMap<String, Object>() {
			// {
			// }
			// });
			Document doc;
			doc = Jsoup.connect(href)
					.userAgent(UrlUtils.userAgent)
					.cookie("Cookie", "UM_distinctid=15d4f9ce60a4eb-050c6be969bcef-35414878-1aeaa0-15d4f9ce60b3bc; CNZZDATA1260136144=1243021942-1500278440-https%253A%252F%252Fwww.baidu.com%252F%7C1501116123; Hm_lvt_21e82dda40c2143d1c3187f1c80935ec=1500279272,1500968826,1501061381; Hm_lpvt_21e82dda40c2143d1c3187f1c80935ec=1501119552")
					.timeout(10000).get();
			System.out.println(TAG+ "url = " + href);

//			Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			try {
				// Element globalnavElement =
				// doc.select("div.adFocusHtml").first();
				Elements moduleElements = doc.select("div.keywordtopsmw");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
							MArticleBean sbean = new MArticleBean();
							try {
								sbean.setHref(href);
								try {
									Element imgElement = moduleElements.get(i);
									if (imgElement != null) {
										String postmeta = imgElement.text();
										System.out.println(TAG+ "i==" + i + ";postmeta==" + postmeta);
										sbean.setPostmeta(postmeta);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								
								try {
									Element imgElement = moduleElements.get(i).previousElementSibling().select("img").first();
									if (imgElement != null) {
										String src = imgElement.attr("src");
										System.out.println(TAG+ "i==" + i + ";src==" + src);
										sbean.setSrc(src);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								
							} catch (Exception e) {
								e.printStackTrace();
							}

							list.add(sbean);
						}
					 
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<MArticleBean> parseFootList(String href, int pageNo) {
		List<MArticleBean> list = new ArrayList<MArticleBean>();
		try {
			// href = makeURL(href, new HashMap<String, Object>() {
			// {
			// }
			// });
			if(pageNo>1){
				//http://m.mm131.com/xinggan/2847.html
				//http://m.mm131.com/xinggan/2847_2.html
				href = href.replace(".html", "_")+pageNo+".html";
			}
			
			Document doc;
			doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			System.out.println(TAG+ "url = " + href);

//			Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			try {
				/**
				 */
				 Element globalnavElement =
				 doc.select("dl.other").first();
				Elements moduleElements = globalnavElement.select("dd");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						Element pElement = moduleElements.get(i);
						 
							MArticleBean sbean = new MArticleBean();
							try {
								try {
									Element aElement = moduleElements.get(i).select("a").first();
									if (aElement != null) {
										String hrefa = aElement.attr("href");
										System.out.println(TAG+ "i==" + i + ";hrefa==" + hrefa);
										sbean.setHref(hrefa);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								 

								try {
									Element imgElement = moduleElements.get(i).select("a").first();
									if (imgElement != null) {
										String alt = imgElement.text();
										System.out.println(TAG+ "i==" + i + ";alt==" + alt);
										sbean.setAlt(alt);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
 

							} catch (Exception e) {
								e.printStackTrace();
							}

							list.add(sbean);
						}
					 
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static MArticleJson parseImagePagerList(String href,int pageNo) {
		MArticleJson mMArticleJson = new MArticleJson();
		List<MArticleBean> list = new ArrayList<MArticleBean>();
		int currentposition = 0;
		try {
			// href = makeURL(href, new HashMap<String, Object>() {
			// {
			// }
			// });
//			if(pageNo>1){
//				//http://m.mm131.com/xinggan/2847.html
//				//http://m.mm131.com/xinggan/2847_2.html
//				href = href.replace(".html", "_")+pageNo+".html";
//			}
//			
			Document doc;
			doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			System.out.println(TAG+ "url = " + href);

//			Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			try {
				/**
				 * <article class="post">
         <div class="post-header">
            <h2 class="mm-title">大波女神悦悦艺术写真尺度大惑力强</h2>
            <div class="post-data"><span class="post-meta">2017-03-06 21:48:39</span></div>
         </div>
         <div class="post-content single-post-content"><
         a href="2847_2.html"><img alt="大波女神悦悦艺术写真尺度大惑力强(图1)"
          src="http://img2.mm131.com:55888/pic/2847/1.jpg" />
         </a></div>
         
		 <div class="fenye">
		 <a href="2848.html" class="rw">上一篇</a>
		 <span class="rw">1/58页</span>
		 <a href="2847_2.html">下一张</a>
		 </div>
		 
         <div class="mmss"><script type="text/javascript" src="http://img1.mm798.net/mixiu/js/ms.js"></script></div>
         <div class="post-footer single-post-footer">
            <span class="post-meta">分类：<a href="http://m.mm131.com/xinggan/">性感美女</a></span>
            <span class="post-tag">标签：<a href="http://m.mm131.com/tag/%B4%F3%B2%A8">大波</a><a href="http://m.mm131.com/tag/%D4%C3%D4%C3">悦悦</a><a href="http://m.mm131.com/tag/%D2%D5%CA%F5%D0%B4%D5%E6">艺术写真</a></span>
         </div>
		 <div class="jcdog"><script type="text/javascript">show_down();</script></div>
      </article>

				 */
				/**
				 * 获取分页信息
				 */
				try {
					Element globalnavElement = doc.select("div.fenye").first();
					if(globalnavElement!=null){
						Element pElement = globalnavElement.select("span.rw").first();
						String pager = pElement.text().replace("页", "");
						currentposition = Integer.parseInt(pager.split("/")[0]);
						int tposition = Integer.parseInt(pager.split("/")[1]);
						mMArticleJson.setCurrentPosition(currentposition);
						mMArticleJson.setMaxPage(tposition);
						for(int i=0;i<tposition;i++){
							MArticleBean sbean = new MArticleBean();
							sbean.setHref(href.replace(".html", "_")+(i+1)+".html");
							list.add(sbean);
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				// Element globalnavElement =
				// doc.select("div.adFocusHtml").first();
				Elements moduleElements = doc.select("article.post");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						Element pElement = moduleElements.get(i);
						 
							MArticleBean sbean = new MArticleBean();
							try {
								try {
									Element aElement = moduleElements.get(i).select("a").first();
									if (aElement != null) {
										String hrefa = aElement.attr("href");
										if(!hrefa.contains(UrlUtils.MM_M)){
											hrefa = href;
										}
										System.out.println(TAG+ "i==" + i + ";hrefa==" + hrefa);
										sbean.setHref(hrefa);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								 

								try {
									Element imgElement = moduleElements.get(i).select("img").first();
									if (imgElement != null) {
										String alt = imgElement.attr("alt");
										if(alt.contains("%u")){
											alt = EscapeUnescapeUtils.unescape(alt);
										}
										System.out.println(TAG+ "i==" + i + ";alt==" + alt);
										sbean.setAlt(alt);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("img").first();
									if (imgElement != null) {
										String dataimg = imgElement.attr("src");
										System.out.println(TAG+ "i==" + i + ";dataimg==" + dataimg);
										sbean.setDataimg(dataimg);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("span.post-meta").first();
									if (imgElement != null) {
										String postmeta = imgElement.text();
										System.out.println(TAG+ "i==" + i + ";postmeta==" + postmeta);
										sbean.setPostmeta(postmeta);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

							} catch (Exception e) {
								e.printStackTrace();
							}

							if(currentposition>0){
								currentposition = currentposition-1;
							}
							list.set(currentposition, sbean);
						}
					 
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		mMArticleJson.setList(list);
		return mMArticleJson;
	}
	
	
	public static MArticleJson parsePXImagePagerList(String href,int pageNo) {
		MArticleJson mMArticleJson = new MArticleJson();
		List<MArticleBean> list = new ArrayList<MArticleBean>();
		int currentposition = 0;
		try {
			// href = makeURL(href, new HashMap<String, Object>() {
			// {
			// }
			// });
//			if(pageNo>1){
//				//http://m.mm131.com/xinggan/2847.html
//				//http://m.mm131.com/xinggan/2847_2.html
//				href = href.replace(".html", "_")+pageNo+".html";
//			}
//			
			Document doc;
			doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent)
					.cookie("Cookie", "UM_distinctid=15d4f9ce60a4eb-050c6be969bcef-35414878-1aeaa0-15d4f9ce60b3bc; CNZZDATA1260136144=1243021942-1500278440-https%253A%252F%252Fwww.baidu.com%252F%7C1501116123; Hm_lvt_21e82dda40c2143d1c3187f1c80935ec=1500279272,1500968826,1501061381; Hm_lpvt_21e82dda40c2143d1c3187f1c80935ec=1501119552")
					.timeout(10000).get();
			System.out.println(TAG+ "url = " + href);

//			Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
//			 System.out.println(doc.toString());
			try {
				
				// Element globalnavElement =
				// doc.select("div.adFocusHtml").first();
				Elements moduleElements = doc.select("div.maxPicBox");
				if(moduleElements==null || moduleElements.size()==0){
					try {
						/**
						 * playlist==				<div class='cont' id='myElement'><script type='text/javascript'>jwplayer('myElement').setup({playlist:'',file:'http://gslb.miaopai.com/stream/JUcrYOTtrE14088zhyf5G~JXl2nZ2ebX.mp4',image:'//img.pximg.com/2017/07/1acac0f5f62fde3.png',width: '100%',aspectratio:'10:6',advertising: {client:'vast',schedule: {'myAds':{'offset':'pre','tag':''}}}})</script></div>					<div style="padding: 5px;">
						 * */
						
						//读取图片信息
						try {
							    URL url = new URL(href);
							    InputStreamReader read = new InputStreamReader(url.openStream(), "utf-8");// 考虑到编码格式
				                BufferedReader bufferedReader = new BufferedReader(read);
				                String lineTxt = null;
				                while ((lineTxt = bufferedReader.readLine()) != null) {//按行读取
				                    if (!"".equals(lineTxt)) {
				                    	if(lineTxt.contains("playlist") && lineTxt.contains("jwplayer(")){
				                    		MArticleBean sbean = new MArticleBean();
				                    		//file:'http://gslb.miaopai.com/stream/JUcrYOTtrE14088zhyf5G~JXl2nZ2ebX.mp4',
				                    		//image:'//img.pximg.com/2017/07/1acac0f5f62fde3.png',width: '100%'
				                    		String file = lineTxt.split("image:")[0];
				                    		String image = lineTxt.split("image:")[1];
				                    		System.out.println("file=="+file+"image="+image);
				                    		
				                    		file = file.split("file:")[1];
				                    		file = file.replace("'", "").replace(",", "");
				                    		sbean.setHref(file);
				                    		
				                    		image = image.split("width:")[0];
				                    		image = "https:"+image.replace("'", "").replace(",", "");
				                    		sbean.setDataimg(image);
				                    		list.add(sbean);
				                    	}
				                        System.out.println(lineTxt);
				                    }
				                }
				                read.close();//关闭InputStreamReader
				                bufferedReader.close();//关闭BufferedReader
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					if (moduleElements != null && moduleElements.size() > 0) {
						for (int i = 0; i < moduleElements.size(); i++) {
								MArticleBean sbean = new MArticleBean();
								try {
									 sbean.setHref(href);
									try {
										Element imgElement = moduleElements.get(i).select("img").first();
										if (imgElement != null) {
											String alt = imgElement.attr("alt");
											if(alt.contains("%u")){
												alt = EscapeUnescapeUtils.unescape(alt);
											}
											System.out.println(TAG+ "i==" + i + ";alt==" + alt);
											sbean.setAlt(alt);
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
	
									try {
										Element imgElement = moduleElements.get(i).select("img").first();
										if (imgElement != null) {
											String dataimg = "https:"+imgElement.attr("src").replace("						", "").replace(" ", "").replace("\t\t", "").replace("\t", "");
											System.out.println(TAG+ "i==" + i + ";dataimg==" + dataimg);
											sbean.setDataimg(dataimg);
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
	
								} catch (Exception e) {
									e.printStackTrace();
								}
	 
								list.add(sbean);
							}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//读取图片信息
		try {
			    URL url = new URL(href);
			    InputStreamReader read = new InputStreamReader(url.openStream(), "utf-8");// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {//按行读取
                    if (!"".equals(lineTxt)) {
                    	if(lineTxt.contains("bigPic") && lineTxt.contains("format/webp")){
                    		MArticleBean sbean = new MArticleBean();
                    		//"bigPic": "//img.pximg.com/2017/07/42e36be241d9db6.jpg!/format/webp"},
                    		String bigpic = lineTxt.replace("\"bigPic\": \"", "").replace("\"},", "");
                    		System.out.println("bigpic=="+bigpic);
                    		bigpic = "https:"+bigpic.replace("						", "").replace(" ", "").replace("\t\t", "").replace("\t", "");
                    		sbean.setDataimg(bigpic);
                    		list.add(sbean);
                    	}
                        System.out.println(lineTxt);
                    }
                }
                read.close();//关闭InputStreamReader
                bufferedReader.close();//关闭BufferedReader
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mMArticleJson.setList(list);
		return mMArticleJson;
	}
	
	
	public static MArticleJson parsePCImagePagerList(String href,int pageNo) {
		MArticleJson mMArticleJson = new MArticleJson();
		List<MArticleBean> list = new ArrayList<MArticleBean>();
		int currentposition = 0;
		try {
			// href = makeURL(href, new HashMap<String, Object>() {
			// {
			// }
			// });
//			if(pageNo>1){
//				//http://m.mm131.com/xinggan/2847.html
//				//http://m.mm131.com/xinggan/2847_2.html
//				href = href.replace(".html", "_")+pageNo+".html";
//			}
//			
			Document doc;
			doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			System.out.println(TAG+ "url = " + href);

//			Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			try {
				/**
				 */
				/**
				 * 获取分页信息
				 */
				try {
					Element globalnavElement = doc.select("div.content-page").first();
					if(globalnavElement!=null){
						Element pElement = globalnavElement.select("span.page-ch").first();
						String pager = pElement.text().replace("共", "").replace("页", "");
						Element nElement = globalnavElement.select("span.page_now").first();
						currentposition = Integer.parseInt(nElement.text());
						
						int tposition = Integer.parseInt(pager);
						mMArticleJson.setCurrentPosition(currentposition);
						mMArticleJson.setMaxPage(tposition);
						for(int i=0;i<tposition;i++){
							MArticleBean sbean = new MArticleBean();
							sbean.setHref(href.replace(".html", "_")+(i+1)+".html");
							list.add(sbean);
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				// Element globalnavElement =
				// doc.select("div.adFocusHtml").first();
				Elements moduleElements = doc.select("div.content-pic");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						Element pElement = moduleElements.get(i);
						 
							MArticleBean sbean = new MArticleBean();
							try {
								try {
									Element aElement = moduleElements.get(i).select("a").first();
									if (aElement != null) {
										String hrefa = aElement.attr("href");
										System.out.println(TAG+ "i==" + i + ";hrefa==" + hrefa);
										sbean.setHref(href);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								 

								try {
									Element imgElement = moduleElements.get(i).select("img").first();
									if (imgElement != null) {
										String alt = imgElement.attr("alt");
										if(alt.contains("%u")){
											alt = EscapeUnescapeUtils.unescape(alt);
										}
										System.out.println(TAG+ "i==" + i + ";alt==" + alt);
										sbean.setAlt(alt);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("img").first();
									if (imgElement != null) {
										String dataimg = imgElement.attr("src");
										System.out.println(TAG+ "i==" + i + ";dataimg==" + dataimg);
										sbean.setDataimg(dataimg);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								 

							} catch (Exception e) {
								e.printStackTrace();
							}

							if(currentposition>0){
								currentposition = currentposition-1;
							}
							list.set(currentposition, sbean);
						}
					 
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		mMArticleJson.setList(list);
		return mMArticleJson;
	}
	
	
	public static List<MArticleBean> parsePCImageList(String href, int pageNo) {
		List<MArticleBean> list = new ArrayList<MArticleBean>();
		try {
			// href = makeURL(href, new HashMap<String, Object>() {
			// {
			// }
			// });
			if(!href.contains("_")){
				if(pageNo>1){
					//http://m.mm131.com/xinggan/2847.html
					//http://m.mm131.com/xinggan/2847_2.html
					href = href.replace(".html", "_")+pageNo+".html";
				}
			}
			
			Document doc;
			doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			System.out.println(TAG+ "url = " + href);

//			Document doc = Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
			// System.out.println(doc.toString());
			try {
				/**
				 */
				// Element globalnavElement =
				// doc.select("div.adFocusHtml").first();
				Elements moduleElements = doc.select("div.content-pic");
				if (moduleElements != null && moduleElements.size() > 0) {
					for (int i = 0; i < moduleElements.size(); i++) {
						Element pElement = moduleElements.get(i);
							MArticleBean sbean = new MArticleBean();
							try {
								try {
									Element aElement = moduleElements.get(i).select("a").first();
									if (aElement != null) {
										String hrefa = aElement.attr("href");
										System.out.println(TAG+ "i==" + i + ";hrefa==" + hrefa);
										sbean.setHref(href);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								 

								try {
									Element imgElement = moduleElements.get(i).select("img").first();
									if (imgElement != null) {
										String alt = imgElement.attr("alt");
										if(alt.contains("%u")){
											alt = EscapeUnescapeUtils.unescape(alt);
										}
										System.out.println(TAG+ "i==" + i + ";alt==" + alt);
										sbean.setAlt(alt);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = moduleElements.get(i).select("img").first();
									if (imgElement != null) {
										String dataimg = imgElement.attr("src");
										System.out.println(TAG+ "i==" + i + ";dataimg==" + dataimg);
										sbean.setDataimg(dataimg);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								try {
									Element imgElement = doc.select("div.place").first();
									if (imgElement != null) {
										String postmeta = imgElement.text();
										System.out.println(TAG+ "i==" + i + ";postmeta==" + postmeta);
										sbean.setMeta(postmeta);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								
								try {
									Element imgElement = doc.select("div.content-msg").first();
									if (imgElement != null) {
										String postmeta = imgElement.text();
										System.out.println(TAG+ "i==" + i + ";postmeta==" + postmeta);
										sbean.setPostmeta(postmeta);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								
							} catch (Exception e) {
								e.printStackTrace();
							}

							list.add(sbean);
						}
					 
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
