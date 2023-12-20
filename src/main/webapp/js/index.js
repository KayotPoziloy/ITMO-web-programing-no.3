const canvasPlot = document.getElementById('canvas_plot'); // инициализируем объект html
const ctx = canvasPlot.getContext('2d'); // задаем полотно в 2d

const canvasPlotWidth = canvasPlot.clientWidth;
const canvasPlotHeight = canvasPlot.clientHeight;

const lineWidth = 8;
const lineHeight = 2;

const xAxis = canvasPlotWidth / 2; // середина по горизонтали
const yAxis = canvasPlotHeight / 2; // середина по вертикали

const radius = 200;

let resultsArray = [];

// Функция записывает значения из таблицы на странице в массив
function tableValues() {
    resultsArray.length = 0;

    let resultsTable = document.getElementById("results:allResults");
    let rows = resultsTable.querySelectorAll('tbody tr');


    rows.forEach(row => {
        let [xValue, yValue, rValue, resultValue] = [...row.querySelectorAll('td p')].map(p => p.innerText.trim());

        if (xValue !== undefined && yValue !== undefined && rValue !== undefined && resultValue !== undefined
            && xValue !== "" && yValue !== "" && rValue !== "" && resultValue !== "") {
            let resultObject = {
                x: xValue,
                y: yValue,
                r: rValue,
                result: resultValue
            };

            resultsArray.push(resultObject);
        }
    });

    console.log(resultsArray);
}

// Отрисовка значений из таблицы
function dotSend() {
    clearCanvas();
    tableValues();

    resultsArray.forEach(function (result) {
        dot(result);
    })
}

// отрисовка точки
function dot(result) {
    const rSplit = 200; // один r это 200 px на полотне
    let x = result.x;
    let y = result.y;
    let r = document.getElementById("values:rValue").value;

    console.log("функция dot " + x, y, r);
    let xValue = x / r * rSplit + xAxis - 2;
    let yValue = - (y / r * rSplit - yAxis + 2);
    // console.log("функция dot координаты в пикселях ", xValue, yValue);

    let checkCircle = x >= 0 && y <= 0 && x * x + y * y <= r * r;
    let checkTriangle = x <= 0 && x >= -r && y >= -r/2 && y <= 0;
    let checkRectangle = x <= 0 && y >= 0 && y <= x/2 + r/2;

    if (checkCircle || checkTriangle || checkRectangle) {
        ctx.beginPath();
        ctx.fillStyle = "blue"
        ctx.fillRect(xValue, yValue, 4, 4,)
        ctx.closePath();
    } else {
        ctx.beginPath();
        ctx.fillStyle = "red"
        ctx.fillRect(xValue, yValue, 4, 4,)
        ctx.closePath();
    }
}

// Функция, определяющая координату попадения точки
function checkPoint(event) {
    dotSend();
    const rSplit = 200; // один r это 200 px на полотне
    // пусть x = 160px rSplit = 200px x = 160/200 = 0,8
    // пусть x = 160/200*r = 0,8*r
    let rValue = document.getElementById("values:rValue").value;
    let rError = document.getElementById("values:rError");

    if (rValue < 1 || rValue > 4 || rValue == null) {
        rError.textContent = "Введите корректное значение от 1 до 4"
    } else {
        rError.textContent = ""
        // координата приходит относительно верхней левой точки в которой координата (0, 0)
        // потому, чтобы сместить точку к реальной координате относительно центра канваса нужно вычитать длину/2 и ширину/2
        const x = event.offsetX - xAxis;
        const y = -(event.offsetY - yAxis);

        let xValue = x / rSplit * rValue; // получаем x относительно r в пикселях
        let yValue = y / rSplit * rValue;

        console.log("относительный x: " + xValue);
        console.log("относительный y: " + yValue);
        console.log("r: " + rValue);

        clickDot(xValue, yValue);
    }
}

// Отрисовка точки по клику
function clickDot(xValue, yValue) {
    // Заносит данные на страницу для отправки на сервер
    pageData(xValue, yValue);
    // Вызывает сервер для отправки на бд и вывода на страницу
    remote();
    // Отрисовка значений из таблицы чз 100 миллисекунд,
    // чтобы сервер успел отработать
    setTimeout(function () {
        dotSend();
    }, 100)

}

// Заносит данные на страницу для отправки на сервер
function pageData(xValue, yValue) {
    let receivedX = document.getElementById("values:receivedX");
    let receivedY = document.getElementById("values:receivedY");

    receivedX.value = xValue.toString();
    receivedY.value = yValue.toString();
}

// функция обновляет канвас
function clearCanvas() {
    ctx.clearRect(0, 0, canvasPlotWidth, canvasPlotHeight);
    axis();
    zone();
    r();
}
clearCanvas();

// Главные оси
function axis() {
    ctx.beginPath();
    // ось Y
    ctx.moveTo(xAxis, 0);
    ctx.lineTo(xAxis, canvasPlotHeight);
    // ось X
    ctx.moveTo(0, yAxis);
    ctx.lineTo(canvasPlotWidth, yAxis);

    ctx.stroke();
    ctx.closePath();
}

// Четверть круга
function circle() {
    ctx.beginPath();

    ctx.arc(xAxis, yAxis, radius, 0, Math.PI/2);
    ctx.lineTo(xAxis, yAxis);
    ctx.fillStyle = "rgba(68,128,112,0.47)";
    ctx.fill();

    ctx.closePath();
}

// Треугольник
function triangle() {

    ctx.beginPath();
    let x1 = xAxis;
    let y1 = yAxis;
    let x2 = xAxis;
    let y2 = yAxis - radius / 2;
    let x3 = xAxis - radius;
    let y3 = yAxis;

    ctx.moveTo(x1, y1);
    ctx.lineTo(x2, y2);
    ctx.lineTo(x3, y3);

    ctx.fillStyle = "rgba(68,128,112,0.47)";
    ctx.fill();
    ctx.closePath();
}

// Квадрат
function square() {
    ctx.beginPath();
    let x1 = xAxis - radius;
    let y1 = yAxis;
    let x2 = xAxis - radius;
    let y2 = yAxis + radius / 2;
    let x3 = xAxis;
    let y3 = yAxis + radius / 2;
    let x4 = xAxis;
    let y4 = yAxis;

    ctx.moveTo(x1, y1);
    ctx.lineTo(x2, y2);
    ctx.lineTo(x3, y3);
    ctx.lineTo(x4, y4)

    ctx.fillStyle = "rgba(68,128,112,0.47)";
    ctx.fill();
    ctx.closePath();
}

// Отрисовка ОДЗ
function zone() {
    circle();
    triangle();
    square();
}

// Отрисовка значений R
function r() {
    ctx.beginPath();
    ctx.fillStyle = "black";
    ctx.font = '10px Arial';
    ctx.fillText("X", canvasPlotWidth-10, yAxis);
    ctx.fillText("Y", xAxis, + 10);

    ctx.font = '15px Arial';
    for (let i = radius; i >= -(radius); i -= radius / 2 ) {

        let litR = " -R"
        let litR2 = " -R/2"

        if (i < radius) {
            litR = " R"
            litR2 = " R/2"
        }

        if (i === radius / 2 || i === -radius / 2) {
            ctx.fillText(litR2, xAxis - i, yAxis);
            ctx.fillText(litR2, xAxis, yAxis + i);
        } else if (i === radius || i === -(radius)) {
            ctx.fillText(litR, xAxis - i, yAxis);
            ctx.fillText(litR, xAxis, yAxis + i);
        } else {
            continue;
        }
        ctx.fillRect(xAxis - i, yAxis - 4, lineHeight, lineWidth);
        ctx.fillRect(xAxis - 4, yAxis - i, lineWidth, lineHeight);

    }
    ctx.closePath();
}