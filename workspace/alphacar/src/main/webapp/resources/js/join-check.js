var join = {
   tag_status : function (tag) {
      var data = tag.val();   // tag 내 입력된 값을 data 변수에 할당
      tag = tag.attr('name');   // tag 의 name 속성의 값을 확인 ex) id, pw, email...
      
      if ( tag == 'id')   		return this.id_status( data );
      else if ( tag == 'pw')    return this.pw_status ( data ); 
      else if ( tag == 'pw_ck')	return this.pw_ck_status ( data );  
      else if ( tag == 'email') return this.email_status ( data );
   }
   , email_status : function ( email ) {
//		var reg = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
		var reg = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
		if (email == '')				return this.common.empty;
		else if (email.match(space)) 	return this.common.space;
		else if (reg.test(email))		return this.email.valid;
		else 							return this.email.invalid;
      				
	}
   , email : {
		valid : { code : 'valid' , desc : "유효한 이메일입니다."}
		, invalid : { code : "invalid", desc : "유효하지 않은 이메일입니다."}
	}
   , pw_ck_status : function ( pw_ck ) {
		if (pw_ck == '')		return this.common.empty;
		else if (pw_ck == $('[name=pw]').val())		return this.pw.equal;
		else					return this.pw.notEqual;	
	}
   , pw_status : function ( pw ) {
		var reg = /[^A-Za-z0-9]/g;
		var upper = /[A-Z]/g, lower = /[a-z]/g, digit = /[0-9]/g;
		if (pw == '')				return this.common.empty;
		else if (pw.match(space)) 	return this.common.space;
		/* 영문 대/소문자. 숫자 입력 여부 확인 */
		else if (reg.test(pw))		return this.pw.invalid;
		/* 5자 이하 입력 여부 확인 */
      	else if (pw.length < 5) 	return this.common.min;
      	/* 10자 이상 입력 여부 확인 */
      	else if (pw.length > 10) 	return this.common.max;
      	/* 영문 대/소문자. 숫자를 모두 포함 (즉, 하나라도 없다면) */
      	else if (!upper.test(pw) || !lower.test(pw) || !digit.test(pw) ) return this.pw.lack;
      	else						return this.pw.valid; 
	}
   , pw : { // pw 의 기준 설정
      invalid : { code : 'invalid' , desc : '비밀번호를 영문 대/소문자. 숫자를 모두 포함'}
      , valid : { code : 'valid' , desc : '사용 가능한 비밀번호입니다.'}
      , lack : { code : 'invalid' , desc : '영문 대/소문자. 숫자를 모두 포함'}
      , equal : {code : 'valid', desc : '비밀번호가 일치합니다.'}
      , notEqual : { code : 'invalid', desc : '비밀번호가 일치하지 않습니다.'}
   }
   , id_status : function ( id ) {   // id 의 상태 확인
      var reg = /[^a-z0-9]/g; //소문자, 숫자 외 입력시
      
      /* 입력값이 없을 경우 */
      if (id == '') return this.common.empty;
      /* 입력값에 공백이 있을 경우 */
      else if (id.match(space))   return this.common.space;
      /* 소문자, 숫자 외 문자 입력 여부 확인 */
      else if (reg.test(id)) return this.id.invalid;
      /* 5자 이하 입력 여부 확인 */
      else if (id.length < 5) return this.common.min;
      /* 10자 이상 입력 여부 확인 */
      else if (id.length > 10) return this.common.max;
      else   return this.id.valid;
   }
   
   , id : { // id 의 기준 설정
      invalid : { code:'invalid', desc : '아이디는 영문 소문자, 숫자만 입력 가능' }
      ,valid : { code : 'valid', desc : '아이디 중복 확인하세요.'}   
      ,usable : { code : 'valid', desc : '사용 가능한 아이디 입니다.'}
      ,unUsable : { code : 'invalid', desc : '이미 사용 중인 아이디입니다' }
   }
   ,common : {
      empty : { code : 'invalid', desc : '입력하세요'}
      , space : { code : 'invalid', desc : '공백없이 입력하세요'}
      , max : { code : 'invalid', desc : '최대 10자 이하로 입력하세요'}
      , min : { code : 'invalid', desc : '최소 5자 이상 입력하세요'}
   }
   ,id_usable : function ( usable ) {
	 if ( usable)	return this.id.usable;
	 else 			return this.id.unUsable;
	}
   
}
var space = /\s/g;   // 전체 문자에 공백이 있는지 확인... space 할당