// 페이지 번호를 자동으로 넣어주는 클라스
package com.ktds.bbs;

import org.springframework.stereotype.Service;

@Service
public class Page {
	private int totalPage, startPage, endPage;
	private int startRow, endRow;
	private StringBuffer sb; // html 문을 담는데 사용

	// 현재 페이지 번호, 글의 총 갯수, 페이지 크기, 페이지 블록 크기
	public Page() {

	}

	// 페이징 처리 함수
	public void paging(int pageNum, int count, int pageSize, int pageBlock) {
		// 크거나 같은 가장 가까운 정수
		totalPage = (int) Math.ceil((double) count / pageSize);

		// ex) 17번 페이지 일 때 게시글은 ( 161~170 번 글이 로드 되어야함 )
		startRow = (pageNum - 1) * pageSize + 1; // 16 * 10 + 1 = 161
		endRow = pageNum * pageSize; // 17 * 10 = 170
		// 게시 글을 가져오는 쿼리문을 날릴 때 매개 변수로 사용된다

		// 현재 선택된 페이지 번호를 이용하여 페이지 블록에 표시해 줄 페이지 계산
		startPage = (int) ((pageNum - 1) / pageBlock) * pageBlock + 1;
		endPage = startPage + pageBlock - 1;

		// 총 페이지보다 마지막 페이지가 큰 수 일 경우 총 페이지의 수가 마지막 페이지가 된다
		if (endPage > totalPage) {
			endPage = totalPage;
		}

		sb = new StringBuffer();
		// 왼쪽 페이지 블록으로 이동!
		if (startPage < pageBlock) {
			sb.append("<img src='images/hot.gif' width='30' height='9'>");
		} else {
			sb.append("<img src='images/hot.gif' width='30' height='9'");
			sb.append(" onclick='location.href=\"list.ktds?pageNum=");
			sb.append(startPage - pageBlock);
			sb.append("\"' style='cursor:pointer'> ");
		}

		sb.append("&nbsp;|");
		// 페이지 번호 출력 문
		for (int i = startPage; i <= endPage; i++) {
			if (i == pageNum) {
				sb.append("&nbsp;&nbsp; <b><font color='#91B7EF'>");
				sb.append(i);
				sb.append("</font></b>");
			} else {
				sb.append("&nbsp;&nbsp; <a href='list.ktds?pageNum=" + i + "'>"
						+ i + "</a>");

			}
		}

		// 오른쪽 페이 블록 이동!
		sb.append("&nbsp;|");
		if (endPage < totalPage) {
			sb.append("<img src='images/hot.gif' width='30' height='9'");
			sb.append(" onclick='location.href=\"list.ktds?pageNum=");
			sb.append(startPage + pageBlock);
			sb.append("\"' style='cursor:pointer'> ");
		} else {
			sb.append("<img src='images/hot.gif' width='30' height='9'>");
		}
	}

	public int getStartRow() {
		return startRow;

	}

	public int getEndRow() {
		return endRow;
	}

	// Stringbuffer 에 저장된 HTML 구문 반환
	public StringBuffer getSb() {
		return sb;
	}
}
