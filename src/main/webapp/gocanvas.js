'use strict'
var canvas = document.getElementById('canvas');
var context = canvas.getContext('2d');
var {width: w, height: h} = canvas;
var unit = w / 14;
context.fillStyle = '#a26012';

context.fillRect(0, 0, w, h);
context.beginPath()
for (let i = 1; i < 14; i++) {
    context.moveTo(unit, unit * i)
    context.lineTo(unit * 13, unit * i)
    context.stroke();
}
for (let i = 1; i < 14; i++) {
    context.moveTo(unit * i, unit)
    context.lineTo(unit * i, unit * 13)
    context.stroke();
}
context.closePath();
for (let i = 0; i < 13; i++) {
    for (let j = 0; j < 13; j++) {
        switch (currentboard[i][j]) {
            case 1:
                context.fillStyle = '#000000';
                context.beginPath();
                context.arc(unit * (i+1), unit * (j+1), unit / 2, 0, Math.PI * 2);
                context.fill();
                context.closePath();
                break;
            case 2:
                context.fillStyle = '#ffffff';
                context.beginPath();
                context.arc(unit * (i+1), unit * (j+1), unit / 2, 0, Math.PI * 2);
                context.fill();
                context.closePath();
                break;
            case 0:
                break;


        }
    }
}
canvas.addEventListener('click', onClick, false);

function onClick(e) {
    let x ;
    let y ;
    x = e.clientX - canvas.offsetLeft;
    y = e.clientY - canvas.offsetTop;
    x = Math.round(x / unit)-1;
    y = Math.round(y / unit)-1;
    if (x !== -1 && x !== 13 && y !== -1 && y !== 13) {
        postForm(x, y);
    }
}

function postForm(x, y) {

    let form = document.createElement('form');
    let request1 = document.createElement('input');
    let request2 = document.createElement('input');
    let request3 = document.createElement('input');
    form.method = 'POST';
    form.action = 'putGoishi';

    request1.type = 'hidden';
    request1.name = 'place1';
    request1.value = x;

    request2.type = 'hidden';
    request2.name = 'place2';
    request2.value = y;

    request3.type = 'hidden';
    request3.name = 'roomname';
    request3.value = roomname;

    form.appendChild(request1);
    form.appendChild(request2);
    form.appendChild(request3);
    document.body.appendChild(form);


    form.submit();
}