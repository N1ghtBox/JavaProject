price = 0;
change = false

function getPrice(priceOf){
    price = parseInt(priceOf);
    transportCost();
}


function sleep(milliseconds) {
  const date = Date.now();
  let currentDate = null;
  do {
    currentDate = Date.now();
  } while (currentDate - date < milliseconds);
}

var picker = new Lightpick({
    field: document.getElementById('myDatepicker'),
    inline:true,
    singleDate: false,
    selectForward: true,
    minDate: moment().add(1,'day'),
    onSelect: function(start, end){
    if(change){
            var str = '';
            str += start ? start.format('MMM DD, YYYY')+'to' : '';
            str += end ? end.format('MMM DD, YYYY') : '...';
            document.getElementById("myDatepicker").value = str;
            document.getElementById("days").value = getNumberOfDays();
            getFinalResult();
            change=false;
            console.log(days);
    }else{
        change=true;
    }

    }
});

function options(name){
    string = "<select id="+name+" name="+name+" onchange='getFinalResult()' required><option disabled selected value=''>No. "+name+"</option>"
    for(i=1;i<11;i++){
        string+="<option value="+i+">"+i+"</option>"
    }
    return string+"</select>";
}

function getFinalResult(people = 1){
    setTimeout(()=>{
        data = getValue("myDatepicker").split("to");
        if(data[0]=="null") return;
        people = parseInt(getValue("people")) ? parseInt(getValue("people")): people;
        result = getNumberOfDays()*people*price+transportCost();
        document.getElementById("cost").innerText = result ? "Final cost: "+result : "Final cost: -";
    },300)
}

function getValue(element){
    return document.getElementById(element).value;
}

function difference(date1, date2) {
  const date1utc = Date.UTC(date1.getFullYear(), date1.getMonth(), date1.getDate());
  const date2utc = Date.UTC(date2.getFullYear(), date2.getMonth(), date2.getDate());
  day = 1000*60*60*24;
  return(date2utc - date1utc)/day
}

function getNumberOfDays(){
    data = getValue("myDatepicker").split("to");
    start = new Date(data[0]);
    end = new Date(data[1]);
    days = difference(start,end)+1;
    console.log(days);
    return days;
}

var a = document.createElement("div");
a.setAttribute("id","selects");
a.innerHTML="<div class='box'>"+options("people")+"</div>";
document.getElementById("a").append(a);


function transportCost() {
        var ele = document.getElementsByName('choice');
        for(i = 0; i < ele.length; i++) {
            if(ele[i].checked){
                 return parseInt(ele[i].value);
            }
        }
}
