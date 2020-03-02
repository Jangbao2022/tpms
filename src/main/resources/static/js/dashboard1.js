/*
Template Name: Admin Pro Admin
Author: Wrappixel
Email: niravjoshi87@gmail.com
File: js
*/

var names = ['必修', '选修'];
var credits = [10, 10];
var classHours = [10, 10];

var chart_data_columns = [[names[0], credits[0]], [names[1], credits[1]]];

var chart_donut_title = "学分";

var draftType = 1;//为1绘制学时,为2绘制学分

var preElement;//前一个被点击的curr行元素

function changeDraftType(type) {
    chart_data_columns = [];
    draftType = type;
    if (type == 1) {
        for (i = 0; i < names.length; i++) {
            chart_data_columns.push([names[i], [credits[i]]]);
        }
        chart_donut_title = '学分';
        document.getElementById("change1").removeAttribute("style")
        document.getElementById("change2").removeAttribute("style")
        document.getElementById("change1").setAttribute("style", "color:#24d2b5")

    } else {
        for (i = 0; i < names.length; i++) {
            chart_data_columns.push([names[i], [classHours[i]]]);
        }
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
    // debugger;
    var dataNames = element.getAttribute("data-names");
    var dataCredits = element.getAttribute("data-credits");
    var dataClassHours = element.getAttribute("data-classHours");
    names = dataNames.split(" ");
    credits = dataCredits.split(" ");
    classHours = dataClassHours.split(" ");
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
                pattern: ['#42e34d', '#E30E08', '#2a43e3', '#e3d92f', '#dce3cb', '#ff713b', '#3fe395']
            }
        }
    );

}

