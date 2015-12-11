package moe.feng.bilinyan.api;

import moe.feng.bilinyan.model.BasicMessage;
import moe.feng.bilinyan.model.VideoSrc;
import moe.feng.bilinyan.model.VideoViewInfo;

public class VideoApi {

	public static BasicMessage<VideoViewInfo> getVideoViewInfo(int av, int page,
	                                                           boolean needReadFav) {
		String url = ApiHelper.getVideoInfoUrl(av, page, needReadFav);
		return ApiHelper.getSimpleUrlResult(url, VideoViewInfo.class);
	}

	public static BasicMessage<VideoSrc> getVideoSrc(int av) {
		String url = ApiHelper.getHTML5Url(String.valueOf(av));
		return ApiHelper.getSimpleUrlResult(url, VideoSrc.class);
	}

}
