price = 0;
change = false
function getPrice(priceOf){
    price = parseInt(priceOf);
    document.getElementById("test").innerHTML = price;
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
    }else{
        change=true;
    }

    }
});
function options(name){
    string = "<select id="+name+" name="+name+" onchange='getFinalResult()'><option disabled selected value=''>No. "+name+"</option>"
    for(i=1;i<11;i++){
        string+="<option value="+i+">"+i+"</option>"
    }
    return string+"</select>";
}
function getFinalResult(people = 1){
    data = getValue("myDatepicker").split("to");
    if(data[0]=="null") return;
    people = parseInt(getValue("people")) ? parseInt(getValue("people")): people;
    result = getNumberOfDays()*people*price+transportCost();
    document.getElementById("cost").innerText = result ? "Final cost: "+result : "Final cost: -";
}
function getValue(element){
    return document.getElementById(element).value;
}
function getNumberOfDays(){
    data = getValue("myDatepicker").split("to");
    start = new Date(data[0]);
    end = new Date(data[1]);
    days = new Date(end-start).getDate();
    return days;
}
var a = document.createElement("div");
a.setAttribute("id","selects");
a.innerHTML="<div class='box'>"+options("people")+"</div></select><br><p id='test' style='color:black;'></p>";
document.getElementById("a").append(a);


function transportCost() {
    var ele = document.getElementsByName('choice');
    for(i = 0; i < ele.length; i++) {
        if(ele[i].checked)
            return parseInt(ele[i].value);
    }
}
