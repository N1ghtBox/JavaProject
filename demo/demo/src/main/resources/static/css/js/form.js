price = 0;
function getPrice(priceOf){
    price = parseInt(priceOf);
}

var picker = new Lightpick({
    field: document.getElementById('myDatepicker'),
    inline:true,
    singleDate: false,
    selectForward: true,
    minDate: moment().add(1,'day'),
    onSelect: function(start, end){
        var str = '';
        str += start ? start.format('MMM DD, YYYY')+'to' : '';
        str += end ? end.format('MMM DD, YYYY') : '...';
        document.getElementById("myDatepicker").value = str;
        document.getElementById("days").value = getNumberOfDays();
        getFinalResult();
    }
});
function options(name){
    string = "<select id="+name+" onchange='getFinalResult()'><option disabled selected value=''>No. "+name+"</option>"
    for(i=1;i<11;i++){
        string+="<option value="+i+">"+i+"</option>"
    }
    return string+"</select>";
}
function getFinalResult(people = 1){
    data = getValue("myDatepicker").split("to");
    if(data[0]=="null") return;
    people = parseInt(getValue("people")) ? parseInt(getValue("people")): people;
    console.log(getNumberOfDays(),price,people)
    document.getElementById("cost").innerText = getNumberOfDays()*people*price ? "Final cost: "+getNumberOfDays()*people*price : "Final cost: -";
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
a.innerHTML="<div class='box'>"+options("people")+"</div>";
document.getElementById("a").append(a);


