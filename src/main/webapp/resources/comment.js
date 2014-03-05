$(document).ready(function() {
	printResult = function(data){
		var result = JSON.stringify(data);
		alert(result);
	};
	
	$('#comm_show').click(function() {
		$.ajax({
			type : "POST",
			url : "/bbs2/comment/showComment.ktds",
			async : true,
			dataType : "json",
			data : {
				article_num : $('#article_num').val()
			},
			success : function(data) {
//				printResult(data);
				var html = "<table width='600' border='1'>";
				//껍질이 하나 더 생겨서 들어올 때는, data에 해당 리스트에 대한 이름을 직접 지정
//				$.each(data.commentList, function(entryIndex, entry) {
				$.each(data, function(entryIndex, entry) {

					html += '<tr class="bbb">';
					html += '<td class="bgcolor1">' + entry.id + '</td>';
					html += '<td class="bgcolor2">' + entry.comment_content + '</td>';
					html += '<td class="bgcolor2">' + entry.write_date + '</td>';
					html += '<td id="abc" class="bgcolor2"><input type="button" onclick="rowDelete(this)" value='+ entry.comment_num+'></td>';
					html += '</tr>';
				});
				html += '</table>';
				$("#show_comment").html(html);
			},
			error : function(xhr) {
				alert("error html = " + xhr.statusText);
			}
		});
	});

	$('.writer').click(function() {
		$.ajax({
			type : "POST",
			url : "/bbs2/comment/writeComment.ktds",
			async : true,
			dataType : "json",
			data : {
				article_num : $('#article_num').val(),
				comment : $('.textcomment').val()
			},
			success : function(data) {
				// json으로 넘어오므로 파싱이 필요없음 data=JSON.parse(data);
				var html = "<table width='600' border='1'>";
				$.each(data, function(entryIndex, entry) {

					html += '<tr class="bbb">';
					html += '<td class="bgcolor1">' + entry.id + '</td>';
					html += '<td class="bgcolor2">' + entry.comment_content + '</td>';
					html += '<td class="bgcolor2">' + entry.write_date + '</td>';
					html += '<td class="bgcolor2"><input type="button" onclick="rowDelete(this)" value='+ entry.comment_num+'></td>';
					html += '</tr>';
				});
				html += '</table>';
				$("#show_comment").html(html);
				
				$('.textcomment').val('');
			},
			error : function(xhr) {
				alert("error html = " + xhr.statusText);
			}
		});
	});
	
	var flag = false;

	$('#btnwrite').click(function() {
		
		if (flag == true) {
			$('#write_show').attr('style', 'display:none');
			flag = false;
		} else {
			$('#write_show').attr('style', 'display:inline');
			flag = true;
		}
		
	});
});

function rowDelete(obj){
	 $.ajax({
			type : "POST",
			url : "/bbs2/comment/delComment.ktds",
			async : true,
			dataType : "json",
			data : {
				article_num : $('#article_num').val(),
				comment_num : $(obj).val()
			},
			success : function(data) {
				// json으로 넘어오므로 파싱이 필요없음 data=JSON.parse(data);
				var html = "<table width='600' border='1'>";
				$.each(data, function(entryIndex, entry) {

					html += '<tr class="bbb">';
					html += '<td class="bgcolor1">' + entry.id + '</td>';
					html += '<td class="bgcolor2">' + entry.comment_content + '</td>';
					html += '<td class="bgcolor2">' + entry.write_date + '</td>';
					html += '<td class="bgcolor2"><input type="button" onclick="rowDelete(this)" value='+ entry.comment_num+'></td>';
					html += '</tr>';
				});
				html += '</table>';
				$("#show_comment").html(html);
			},
			error : function(xhr) {
				alert("error html = " + xhr.statusText);
			}
		});
}