/*
Template Name: Admin Pro Admin
Author: Wrappixel
Email: niravjoshi87@gmail.com
File: js
*/

//总共必修学分
var totalObligatoryCredit = 10;
//总共必修学时
var totalObligatoryClassHour = 10;
//总共选修学分
var totalElectiveCredit = 10;
//总共选修学时
var totalElectiveClassHour = 10;

var chart_data_columns = [['必修学分', totalObligatoryCredit],
    ['选修学分', totalElectiveCredit]];

var chart_donut_title = "学分";

var draftType = 1;//为1绘制学时,为2绘制学分

var preElement;//前一个被点击的curr行元素

function changeDraftType(type) {
    draftType = type;
    if (type == 1) {
        chart_data_columns = [['必修学分', totalObligatoryCredit],
            ['选修学分', totalElectiveCredit]];
        chart_donut_title = '学分';
        document.getElementById("change1").removeAttribute("style")
        document.getElementById("change2").removeAttribute("style")
        document.getElementById("change1").setAttribute("style", "color:#24d2b5")

    } else {
        chart_data_columns = [['必修学时', totalObligatoryClassHour],
            ['选修学时', totalElectiveClassHour]];
        chart_donut_title = '学时';
        document.getElementById("change1").removeAttribute("style")
        document.getElementById("change2").removeAttribute("style")
        document.getElementById("change2").setAttribute("style", "color:#24d2b5")

    }
    draftCircle()
}

function draft(element) {
    if (preElement != null) {
        preElement.removeAttribute("style")
    }
    element.setAttribute("style", "color:#24d2b5");
    preElement = element;

    var studyData = element.getAttribute("data-study");
    var data = studyData.split(" ");
    totalObligatoryCredit = data[0];
    totalObligatoryClassHour = data[1];
    totalElectiveCredit = data[2];
    totalElectiveClassHour = data[3];

    changeDraftType(1);
}

$(draftCircle);

function draftCircle(type) {
    "use strict";
    var chart = c3.generate({
            bindto: '#visitor',
            data: {
                columns: chart_data_columns,

                type: 'donut',
            },
            donut: {
                label: {
                    show: false
                },
                title: chart_donut_title,
                width: 20,
            },

            legend: {
                hide: false
            },
            color: {
                pattern: ['#42e34d', '#24d2b5']
            }
        }
    );

}

