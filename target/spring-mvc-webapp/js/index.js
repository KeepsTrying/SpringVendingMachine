/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {


    $('.addCredit').on('click', function calcAndDisplayCredit() {
        $('#messageBox > p').text('');
        $('#changeReturnDisplay').text('');
        var creditDisplay = $('#creditDisplay > p').text();
        var currentCredit = parseFloat(creditDisplay.slice(1));
        var addedCredit = parseFloat($(this).attr('id'));

        currentCredit += addedCredit;

        $('#creditDisplay > p').text('$' + currentCredit.toFixed(2));
        $('#currentBalance').val(currentCredit.toFixed(2));
    })

//    $('#makePurchase').on('click', function selectItem() {
//        $('.merchDiv').each(function (index) {
//            if ($(this).attr('id') == slotNum) {
//                hasItem++;
//            }
//        })
//
//        if (hasItem != 1) {
//            $('#messageDisplay > p').text("Please select an available item.");
//        }
//
//    })




    $('#merchDisplay').on('mouseenter', 'div', function () {
        $(this).css('background-color', 'CornflowerBlue');
    })


    $('#merchDisplay').on('mouseleave', 'div', function () {
        $(this).css('background-color', '');
    })


    $('#merchDisplay').on('click', 'div', function selectItem() {
        var selection = this.id;
        $('#itemNumEntry').val(selection);
        
        var creditDisplay = $('#creditDisplay > p').text();
        var currentCredit = parseFloat(creditDisplay.slice(1));
        $('#currentBalance').val(currentCredit.toFixed(2));
    })






    $('#changeReturnButton').on('click', function returnChange() {
        var creditDisplay = $('#creditDisplay > p').text();
        var currentCredit = parseFloat(creditDisplay.slice(1)).toFixed(2);
        var pennies = (currentCredit * 100).toFixed(0);

        if (currentCredit > 0) {

            var quarters = parseInt(pennies / 25);
            pennies = pennies % 25;

            var dimes = parseInt(pennies / 10);
            pennies %= 10;

            var nickels = parseInt(pennies / 5);
            pennies %= 5;

            var changeDisplay = '';


            if (quarters > 0) {
                changeDisplay += 'Quarters: ' + quarters + '<br/>';
            }

            if (dimes > 0) {
                changeDisplay += 'Dimes: ' + dimes + '<br/>';
            }

            if (nickels > 0) {
                changeDisplay += 'Nickels: ' + nickels + '<br/>';
            }

            if (pennies > 0) {
                changeDisplay += 'Pennies: ' + pennies + '<br/>';
            }

            $('#creditDisplay > p').text('$0.00');
            $('#changeReturnDisplay').html(changeDisplay);
            $('#messageBox > p').text("Don't forget your change!");
        }

    });




});


