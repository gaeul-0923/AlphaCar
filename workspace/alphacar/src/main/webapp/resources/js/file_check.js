/** 첨부 파일 관련 처리 함수 */

// 첨부 파일 선택시
$(document).on('change', '#attach-file', function () {
	var attached = this.files[0]; 
	
	if ( attached ) {	// 첨부된 파일이 있을 경우
		$('#file-name').text(attached.name);
		$('#delete-file').css('display', 'inline');
		
		/* #preview 태그가 존재하는지 */
		if ($('#preview').length > 0 ) {
			if ( isImage(attached.name) ) { // 이미지 파일이면 미리보기 처리함.
				$('#preview').html('<img src="" id="preview-img"/>');
				
				var reader = new FileReader();
				reader.onload = function (e) {
					$('#preview-img').attr('src', e.target.result);
				}
				reader.readAsDataURL ( attached );
			// 이미지 파일이 아니면 미리보기 처리하지 않음
			} else 
				$('#preview').html(''); 
		}
		
	} else {			// 첨부된 파일이 없을 경우
		$('#file-name').text('');
		$('#delete-file').css('display', 'none');		
	}
}).on ('click', '#delete-file', function () { // 첨부 파일 삭제시
	if ($('#preview').length > 0) $('#preview').html('');	// id=preview가 있으면
	$('#file-name').text('');
	$('#delete-file').css('display', 'none');
	
	$('#attach-file').val('');	// 파일 태그의 첨부된 파일 정보 없애기
	
})

/* 이미지 파일 여부 확인 */
function isImage(filename) {
	// abc.txt, abc.png, abc.jpg, abc.hwp, 123.abc.tiff ... 
	var ext = filename.substring( filename.lastIndexOf('.') +1 ).toLowerCase();
	
	// 확장자 png, jpg, jpeg, gif, bmp, pcx, tiff
	var imgs = [ 'png', 'jpg', 'jpeg', 'gif', 'bmp', 'pcx', 'tiff' ];
	
	if ( imgs.indexOf( ext ) > -1) return true;
	else return false;
}












