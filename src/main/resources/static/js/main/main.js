$(function (params) {
  /*左侧导航栏显示隐藏功能*/
$(".subNav").click(function(){				
  /*显示*/
  if($(this).find("span:first-child").attr('class')=="title-icon glyphicon glyphicon-chevron-down"){
    $(this).find("span:first-child").removeClass("glyphicon-chevron-down");
      $(this).find("span:first-child").addClass("glyphicon-chevron-up");
      $(this).removeClass("sublist-down");
    $(this).addClass("sublist-up");
  }else{
      /*隐藏*/
    $(this).find("span:first-child").removeClass("glyphicon-chevron-up");
    $(this).find("span:first-child").addClass("glyphicon-chevron-down");
    $(this).removeClass("sublist-up");
    $(this).addClass("sublist-down");
  }	
// 修改数字控制速度， slideUp(500)控制卷起速度
  $(this).next(".navContent").slideToggle(300).siblings(".navContent").slideUp(300);
})
})