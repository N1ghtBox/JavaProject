document.getElementById("body").onscroll = function() {
        var scrolltotop = document.scrollingElement.scrollTop;
        var target = document.getElementById("scroll");
        var xvalue = "center";
        var factor = 0.6;
        var yvalue = Math.round(scrolltotop * factor,2);
        target.style.backgroundPosition = xvalue + " " + yvalue + "px";
        target.style.opacity = 1-yvalue*0.003;
}
