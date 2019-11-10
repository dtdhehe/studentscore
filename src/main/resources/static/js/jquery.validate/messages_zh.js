(function( factory ) {
	if ( typeof define === "function" && define.amd ) {
		define( ["jquery", "../jquery.validate"], factory );
	} else {
		factory( jQuery );
	}
}(function( $ ) {

/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
 */
$.extend($.validator.messages, {
	required: "这是必填字段",
	remote: "请修正此字段",
	email: "请输入有效的电子邮件地址",
	url: "请输入有效的网址",
	date: "请输入有效的日期",
	dateISO: "请输入有效的日期 (YYYY-MM-DD)",
	number: "请输入有效的数字",
	digits: "只能输入数字",
	creditcard: "请输入有效的信用卡号码",
	equalTo: "你的输入不相同",
	extension: "请输入有效的后缀",
	maxlength: $.validator.format("最多可以输入 {0} 个字符"),
	minlength: $.validator.format("最少要输入 {0} 个字符"),
	rangelength: $.validator.format("请输入长度在 {0} 到 {1} 之间的字符串"),
	range: $.validator.format("请输入范围在 {0} 到 {1} 之间的数值"),
	max: $.validator.format("请输入不大于 {0} 的数值"),
	min: $.validator.format("请输入不小于 {0} 的数值"),
	cv_eqPassword: "密码不一致！",
	cv_password2: "请输入至少6个字母、数字或半角的标点符号(除空格)",
	cv_singleType: "请在半角的状态下输入",
	cv_minLength: "最少输入 {0} 个字符。",
	cv_length: "内容长度介于{0}和{1}之间.",
	cv_bytesLength: "内容长度不能超过{0}个字节。",
	cv_phone: "格式不正确,请使用下面格式:020-88888888",
	cv_mobile: "手机号码格式不正确(正确格式如：13450774432)",
	cv_phoneOrMobile: "请填入手机或电话号码,如13688888888或020-8888888",
	cv_idcard: "身份证号码格式不正确",
	cv_idcardDetail: "身份证号码不正确,请重新输入",
	cv_englishOrNum:"请输入英文字母或数字",
	cv_floatOrInt: "请输入数字，并保证格式正确",
	cv_currency: "货币格式不正确",
	cv_qq: "QQ号码格式不正确(正确如：453384319)",
	cv_integer: "请输入整数",
	cv_maxValue: "输入的数值不能超过{0}",
	cv_minValue: "输入的数值不能小于{0}",
	cv_lagerThan: "输入的数值必须大于{0}",
	cv_scale: "请输入数字并保留{0}位小数",
	cv_scalefromTo: "请输入数字并保留{0}-{1}位小数",
	cv_money: "首位以{0}符号开头",
	cv_thou: "格式请写成11,222,333",
	cv_chinese: "请输入中文",
	cv_english: "请输入英文",
	cv_integerOrx: "请输入长度为9位数字或者长度为8位数字以X/x结尾的字符",
	cv_unnormal: "输入值不能为空格和包含其他非法字符",
	cv_username: "用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）",
	cv_faxno: "传真号码不正确",
	cv_zip: "邮政编码格式不正确",
	cv_ip: "IP地址格式不正确",
	cv_allName: "请输入姓名",
	cv_carNo: "车牌号码无效（例：粤J12350）",
	cv_carenergin: "发动机型号无效(例：FG6H012345654584)",
	cv_email2: "请输入有效的电子邮件账号(例：abc@126.com)",
	cv_msn: "请输入有效的msn账号(例：abc@hotnail(msn/live).com)",
	cv_department: "请输入部门排序号(例：1)",
	cv_isdateAfter: "结束日期必须大于等于开始日期",
	cv_isBeforeToday: "日期必须在今天之前",
	cv_isAfterToday: "日期必须在当前之后",
	cv_isAfterNow: "时间必须在当前之后",
	cv_isBeforeNow: "时间必须在当前之前",
	cv_isEqualOrAfterToday: "日期必须在今天或者今天之后",
	cv_isEqualOrBeforeToday: "日期必须在今天或者今天之前",
	cv_isEqualOrAfterSysday: "日期必须在今天或者今天之后",
	cv_isBeforeSysday: "时间必须在今天之前",
	cv_yearMoreThan: "开始年份不得大于结束年份",
	cv_checked: "请选择一个数据项",
	cv_requireRadio: "请选择一个数据项",
	cv_expression: "请输入正确的表达式（例：A * B - (C -D) + E.pow(F) + G / H）"
});

}));