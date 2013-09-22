function timer(){
  var now = new Date();//创建日期对象
  var yy ='2' + now.getYear()-100;//年
  var mm = now.getMonth();//月
  var m = new Array();//以文字形式放到个数组里
   m[0]="1";
   m[1]="2";
   m[2]="3";
   m[3]="4";
   m[4]="5";
   m[5]="6";
   m[6]="7";
   m[7]="8";
   m[8]="9";
   m[9]="10";
   m[10]="11";
   m[11]="12";
   mm = m[mm];
  var day = now.getDate();//日 
  var hh = now.getHours();//小时
  var minu = now.getMinutes();//分钟
  var ss = now.getSeconds();//秒
                //判断分和秒 如是各位数 前面加个0 例如11:23:07
  if(minu<=9){
    minu ='0'+minu;
  }
  if(ss<=9){ss = '0'+ss;}
  nowTime = yy+'-'+mm+'-'+day+'  '+hh+':'+minu+':'+ss+'';
                //判断浏览器
  if(document.layers){
       document.layers.clock.document.write(nowTime);
   document.layers.clock.document.close();
  }
  if(document.all){
   document.getElementById("clock").innerHTML=nowTime;
  }else{
   document.getElementById("clock").innerHTML=nowTime;
  }
                //设置定时器，每秒刷新一次此函数
  window.setTimeout("timer()",1000);
 }