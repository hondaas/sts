<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="/melon/static/css/detail_layout.css" />
<script type="text/javascript"
	src="/melon/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$()
			.ready(
					function() {

						$("#like")
								.click(
										function() {
											$
													.post(
															"/melon/music/addLike",
															{

																"musicId" : "${param.musicId}"

															},
															function(response) {
																var jsonObj = JSON
																		.parse(response);
																if (jsonObj.status == "success") {

																	var asd = parseInt($(
																			"#count")
																			.text());

																	asd = asd + 1

																	$("#count")
																			.text(
																					asd);

																	$("#like")
																			.attr(
																					"src",
																					"/melon/static/img/ic_favorite_black_24dp_2x.png");

																}

															});

										});

						$("#player").css({

							width : '300px',
							height : '500px',
							'text-align' : 'center',
							'vertical-align' : 'center',
							border : '1px solid #DDDDDD',
							padding : '10px',
							'margin' : 'auto'

						});
						$("#title").css({

							display : 'inline-block',
							'margin-bottom' : '0px'

						});
						/* $("#range").css({

							width : '250px'

						}); */

					});
</script>
</head>
<body>


	<span id="count">${music.likeCount}</span> 남은 포인트 :
	${sessionScope._USER_.userPoint}
	<div id="player">

		<img src="/melon/static/img/ic_audiotrack_black_24dp_2x.png"
			id="shuffle" width="30px" height="30px" />
		<h1 id='title'>${music.title}</h1>
		<h3>${music.albumVO.artistVO.member}</h3>
		<hr>
		<img src="/melon/album/post?albumId=${music.albumId}" width="150"
			height="150" /> <br /> <input id="range" type="range" /><br />
		<div class="controlImgs">
			<pre>
				<img src="/melon/static/img/ic_repeat_black_24dp_2x.png" id="repeat"
					width="30px" height="30px" />         <img
					src="/melon/static/img/ic_favorite_border_black_24dp_2x.png"
					id="like" width="30px" height="30px" />         <img
					src="/melon/static/img/ic_shuffle_black_24dp_2x.png" id="shuffle"
					width="30px" height="30px" />
			</pre>
			<br />
			<textarea>${music.lyrics}</textarea>
			<br />

			<pre>
				<img src="/melon/static/img/ic_skip_previous_black_24dp_2x.png"
					id="prev" />       <img
					src="/melon/static/img/ic_play_arrow_black_24dp_2x.png"
					id="playPause" />       <img
					src="/melon/static/img/ic_skip_next_black_24dp_2x.png" id="next" />
			</pre>
		</div>

	</div>
</body>
</html>