<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#bbs table {
	    width:580px;
	    margin:0 auto;
	    margin-top:20px;
	    border:1px solid black;
	    border-collapse:collapse;
	    font-size:14px;
	    
	}
	
	#bbs table caption {
	    font-size:20px;
	    font-weight:bold;
	    margin-bottom:10px;
	}
	
	#bbs table th {
	    text-align:center;
	    border:1px solid black;
	    padding:4px 10px;
	}
	
	#bbs table td {
	    text-align:left;
	    border:1px solid black;
	    padding:4px 10px;
	}
	
	.no {width:15%}
	.subject {width:30%}
	.writer {width:20%}
	.reg {width:20%}
	.hit {width:15%}
	.title{background:lightsteelblue}
	.odd {background:silver}
</style>
<script type="text/javascript">
	function sendData(f) {
		
		// 유효성 검사
		/*
		for (var i = 0; i < f.elements.length; i++) {
			if(f.elements[i].value ==""){
				if(i ==3) continue;
				alert(f.elements[i].name +"를[을] 입력하세요")
				f.elements[i].focus;
				return;
			}
		}
		*/
		
		// 다른 방법
		if(f.subject.value.trim().length <=0){
			alert("제목을 입력하세요");
			f.subject.focus;
			return;
		}
		if(f.writer.value.trim().length <=0){
			alert("작성자를 입력하세요");
			f.subject.focus;
			return;
		}
		if(f.content.value.trim().length <=0){
			alert("내용을 입력하세요");
			f.subject.focus;
			return;
		}
		if(f.pwd.value.trim().length <=0){
			alert("비밀번호를입력하세요");
			f.subject.focus;
			return;
		}
		

		f.action="/MyController?cmd=write_ok";
		f.submit();
	}
	function list_go(f) {
		f.action="/MyController?cmd=list";
		f.submit();
	}
</script>
</head>
<body>
	<div id="bbs">
	<form method="post" encType="multipart/form-data">
	<!-- mult에는 hidden을 사용못한다.그래서 f.action으로 -->
		<table summary="게시판 글쓰기">
			<caption>게시판 글쓰기</caption>
			<tbody>
				<tr>
					<th>제목:</th>
					<td><input type="text" name="subject" size="45"/></td>
				</tr>
				<tr>
					<th>이름:</th>
					<td><input type="text" name="writer" size="12"/></td>
				</tr>
				<tr>
					<th>내용:</th>
					<td><textarea name="content" cols="50" 
							rows="8"></textarea></td>
				</tr>
				<tr>
					<th>첨부파일:</th>
					<td><input type="file" name="f_name"/></td>
				</tr>
				<tr>
					<th>비밀번호:</th>
					<td><input type="password" name="pwd" size="12"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="보내기" onclick="sendData(this.form)"/>
						<input type="reset" value="다시"/>
						<input type="button" value="목록" onclick="list_go(this.form)"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	</div>
</body>
</html>

