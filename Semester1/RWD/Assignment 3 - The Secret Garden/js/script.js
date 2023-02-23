//LET THE MAGIC HAPPEN!!!

/* EXTRA FUNCTIONALITIES:

DANIEL: Whenever you catch (hover) the butterfly,
the counter goes up by 1. Also, when you catch apples,
you get 2 points.

MATAS: Twitter is flying around. Cancel (click) it
& get over a million points!

DRAGOS: Whenever you water the grass, it will grow.

LAURA: The butterfly is now a lovely little gif!
And she flips depending on which direction she's going :)
Source for the gif: https://www.deviantart.com/ngochan192/art/Butterfly-Gif-804068978
*/ 


//initialization of useful variables
var maxWidth = $(window).width();
var maxHeight = $(window).height();
var butterflyWidth = $("#butterfly").width();
var butterflyHeight = $("#butterfly").height();
var treeX = $("#tree").offset().left + 100;
var treeY = $("#tree").offset().top + 100;
var treeWidth = $("#tree").width() - 200 ;
var treeCrownHeight = $("#tree").height()*(0.5) - 100;
var basketX = $(".basket").offset().left;
var basketY = $(".basket").offset().top;
var basketWidth = $(".basket").width() - 50;
var waterdropSpawnX = $("#wateringcan").offset().left - 10;
var waterdropSpawnY = $("#wateringcan").offset().top + 80;
var counter = 0;
var birdWidth = $("#bird").width();
var birdHeight = $("#bird").height();
var grow = 100;


//net following cursor function;
//source: https://jsfiddle.net/lesson8/SYwba/
$(document).mousemove(function(e) { 
    $("#net").offset({left:e.pageX, top:e.pageY});
});


butterflyIdle(); //call to the idle butterfly animation

//definition of the idle butterfly animation
function butterflyIdle() { 
    var newLeftCoord = Math.random()*(maxWidth - butterflyWidth);
    var newTopCoord = Math.random()*(maxHeight - butterflyHeight);
    // if else statement: will make the butterfly flip towards the direction in which it flies
    if (newLeftCoord < $("#butterfly").offset().left)
    {
        $("#butterfly").addClass("mirror");
    }
    else
    {
        $("#butterfly").removeClass("mirror");
    }
    $("#butterfly").animate({
        left: newLeftCoord,
        top: newTopCoord
    }, 4000, function() {butterflyIdle();});
}

//butterfly getting frightened whenever someone hovers it
$("#butterfly").mouseover(function() { 
    $(this).stop();
    var xCoord = Math.random()*(maxWidth - butterflyWidth);
    var yCoord = Math.random()*(maxHeight - butterflyHeight);
    // if else statement: will make the butterfly flip towards the direction in which it flies
    if (xCoord < $("#butterfly").offset().left)
    {
        $("#butterfly").addClass("mirror");
    }
    else
    {
        $("#butterfly").removeClass("mirror");
    }
$(this).animate({left: xCoord, top: yCoord}, 250);
// the timeout below is important so that the
//butterfly doesn't flip early and look weird lol
setTimeout(function(){ butterflyIdle(); }, 250); 


//counter going up by 1 whenever the butterfly is "caught"
counter ++;
$('span').html(counter);
});

//call to the apples being randomly generated whenever the
//website is loaded :))
spawnApples();

function spawnApples() {
    $("#apple1").offset({
        left: treeX + Math.random()*treeWidth,
        top: treeY + Math.random()*treeCrownHeight});
    
    $("#apple2").offset({
        left: treeX + Math.random()*treeWidth,
        top: treeY + Math.random()*treeCrownHeight});

        $("#apple3").offset({
            left: treeX + Math.random()*treeWidth,
            top: treeY + Math.random()*treeCrownHeight});
}

//Apple going to the basket whenever it is clicked
$(".apple").click(function () {
    $(this).animate({
        left: basketX + Math.random()*basketWidth,
        top: basketY + 170
        
    })
    //Counter going up by 2 whenever apples are caught
    counter = counter + 2;
    $('span').html(counter);
})

//Watering can tilting whenever it is clicked
//with the drops implemented as intended :))
//The grass growing also happens here.
$("#wateringcan").click(function () {
    if ($(this).attr("src") == "images/wateringcan.png")
    {
        $(this).attr("src","images/wateringcantilt.png");
        $(".waterdrop").stop();
        $(".waterdrop").show();
        goBackToSpawn("#waterdrop1");
        goBackToSpawn("#waterdrop2");
        goBackToSpawn("#waterdrop3");
        document.body.style.backgroundSize = (600 + grow) +"px";
        grow += 100;
    }

    else
    {
        $(this).attr("src", "images/wateringcan.png");
        dropsFinishing();
    }
})

// this function had to be defined for the drops to still
// finish when the watering can goes back 2 normal but the
// drops are in the air :)
function dropsFinishing() {
    var speed = 1000 + Math.random()*1000
    $(".waterdrop").stop();
    $(".waterdrop").animate({
        top: $(window).height()
    },speed,"linear");
}

//bring the drops back to a slightly random position
//on the tip of the watering can :)
function goBackToSpawn(id) {
    $(id).offset({
        left: waterdropSpawnX + Math.random()*20,
        top: waterdropSpawnY + Math.random()*30
    });
    fallDown(id);
}

//drops falling down with slightly random speeds so they
//don't all fall at the same time&speed :)
function fallDown(id) {
    $(id).animate({
        top: $(window).height()
    },1000 + Math.random()*1000, function() {goBackToSpawn(id)});
    
}

//twitter going around
function birdieIdle() { 
    $("#bird").animate({
        left: Math.random()*(maxWidth - birdWidth),
       
    }, 4000, function() {birdieIdle();});
}
birdieIdle();

//cancelling twitter by clicking him !!!
$("#bird").click(function() { 
   
    $(this).stop();
    $(this).fadeOut(1000);
    $("#order").text("Did you just cancel twitter?! Have a million points!!");
    counter +=1000000000;
    $('span').html(counter);

})

//thank you for going thru our code hehe :)