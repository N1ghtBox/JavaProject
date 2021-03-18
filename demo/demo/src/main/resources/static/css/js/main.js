function ScrollFade(factorFade) {
        var scrolltotop = document.scrollingElement.scrollTop;
        var target = document.getElementById("scroll");
        var xvalue = "center";
        var factor = 0.6;
        var yvalue = scrolltotop * factor;
        target.style.backgroundPosition = xvalue + " " + yvalue + "px";
        target.style.opacity = 1-yvalue*factorFade;
}
