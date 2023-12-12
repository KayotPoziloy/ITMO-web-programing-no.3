// Функция, получающая значения X, Y, R из формы на index.jsp
function getFormValues() {
    let xValue = document.getElementById("xValue").value;
    xValue = xValue.replace(',', '.');
    let yValue = document.getElementById("yValue").value;
    yValue = yValue.replace(',', '.');
    let rValue = document.getElementById("rValue").value;
    rValue = rValue.replace(',', '.');

    // console.log("X:", xValue);
    // console.log("Y:", yValue);
    // console.log("R:", rValues);

    return {x: xValue, y: yValue, r: rValue};
}

// Функция для валидации значений X, Y и R
function validateForm(xValue, yValue, rValue) {
    // let errorX = document.getElementById("errorX");
    // let errorY = document.getElementById("errorY");
    // let errorR = document.getElementById("errorR");
    // // console.log("r в validateForm", rValues);
    //
    // // Сбрасываем текст ошибок
    // errorX.textContent = "";
    // errorY.textContent = "";
    // errorR.textContent = "";

    // if (isNaN(xValue) || xValue < -2 || xValue > 1.5) {
    //     errorX.textContent = "Введите корректное значение X (от -2 до 1.5).";
    //     return false;
    // }
    //
    // if (isNaN(yValue) || yValue < -3 || yValue > 3) {
    //     errorY.textContent = "Введите корректное значение Y (от -3 до 3).";
    //     return false;
    // }
    //
    //
    // if (isNaN(rValue) || rValue < 1 || rValue > 4) {
    //     errorR.textContent = "Введите корректное значение R (от 1 до 4).";
    //     return false;
    // }

    return true;
}

function submitForm(xValue, yValue, rValue, isCanvas) {
    console.log("отправка");
    // let url;
    // // Формируем URL с параметрами
    // if (isCanvas) {
    //     url = "/webLab2_war/AreaCheckServlet?x=" + xValue + "&y=" + yValue + "&r=" + rValue;
    // } else {
    //     url = "/webLab2_war/controller?x=" + xValue + "&y=" + yValue + "&" + rValues.map(r => `r=${r}`).join('&');
    //     console.log(url);
    // }
    //
    // // Создаем XMLHttpRequest объект
    // let xhr = new XMLHttpRequest();
    //
    // xhr.open("GET", url, true);
    //
    // xhr.onload = function () {
    //     if (xhr.status === 200) {
    //         console.log("исправлено");
    //         let responseText = xhr.responseText;
    //         responseText = responseText.replace(/}{/g, '}\n{');
    //         let jsonStrings = responseText.split('\n');
    //         let results = jsonStrings.map(json => JSON.parse(json));
    //         updateResultTable(results);
    //         arraySave(results);
    //         // отправлять results в index.js, для дальнейшей отрисовки координаты
    //     } else {
    //         console.error("Ошибка при отправке данных на сервер");
    //     }
    // }
    //
    // xhr.send();
}

function updateResultTable(results) {
    let resultTable = document.getElementById("resultTable");
    results.forEach(result => {
        let newRow = resultTable.insertRow(1);
        let xCell = newRow.insertCell(0);
        let yCell = newRow.insertCell(1);
        let rCell = newRow.insertCell(2);
        let resultCell = newRow.insertCell(3);

        xCell.innerHTML = result.x;
        yCell.innerHTML = result.y;
        rCell.innerHTML = result.r;
        resultCell.innerHTML = result.isInside ? "Да" : "Нет";
    })
}

// Главная функция, которая вызывает остальные функции и управляет процессом
function processForm() {
    let {x, y, r} = getFormValues();
    // console.log(r);

    if (validateForm(x, y, r)) {
        submitForm(x, y, r, false);
    }
}