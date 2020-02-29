/*
Template Name: Admin Pro Admin
Author: Wrappixel
Email: niravjoshi87@gmail.com
File: js
*/

//总共必修学分
var totalObligatoryCredit = 20;
//缺少必修学分
var lackObligatoryCredit = 0;

//总共必修学时
var totalObligatoryClassHour = 30;
//缺少必修学分
var lackObligatoryClassHour = 0;

//总共选修学分
var totalElectiveCredit = 10;
//缺少选修学分
var lackElectiveCredit = 0;

//总共选修学时
var totalElectiveClassHour = 10;
//缺少选修学时
var lackElectiveClassHour = 0;

var chart_data_columns = [['必修学分', totalObligatoryCredit],
        ['选修学分', totalElectiveCredit],
        ['缺少必修学分', lackObligatoryCredit],
        ['缺少选修学分', lackElectiveCredit],
    ]
;

var chart_donut_title = "学分";

var draftType = 1;//为1绘制学时,为2绘制学分

var preElement;//前一个被点击的curr行元素

function changeDraftType(type) {
    draftType = type;
    if (type == 1) {
        chart_data_columns = [['必修学分', totalObligatoryCredit],
            ['选修学分', totalElectiveCredit],
            ['缺少必修学分', lackObligatoryCredit],
            ['缺少选修学分', lackElectiveCredit],
        ]
        chart_donut_title = '学分';
        document.getElementById("change1").removeAttribute("style")
        document.getElementById("change2").removeAttribute("style")
        document.getElementById("change1").setAttribute("style", "color:#24d2b5")

    } else {
        chart_data_columns = [['必修学时', totalObligatoryClassHour],
            ['选修学时', totalElectiveClassHour],
            ['缺少必修学时', lackObligatoryClassHour],
            ['缺少选修学时', lackElectiveClassHour],
        ]
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

    lackObligatoryCredit = data[4];
    lackObligatoryClassHour = data[5];
    lackElectiveCredit = data[6];
    lackElectiveClassHour = data[7];

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
                pattern: ['#42e34d', '#24d2b5', '#d27b4d', '#ff1210']
            }
        }
    );

}

