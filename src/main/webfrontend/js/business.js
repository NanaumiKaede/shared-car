$().ready(() => {
    let user = JSON.parse(window.sessionStorage.getItem("user"))
    if (user != null) {
        $('#name').val(user.name);
        $('#id_card').val(user.idCard);
        $('#driver_id').val(user.driverId);
    } else {
        window.location.href = 'login.html';
    }
})

$('#file').fileinput({
    language: 'zh',
    uploadUrl: 'http://127.0.0.1:8080/upload',
    allowedFileExtensions: ['jpg', 'gif', 'png'],
    showCaption: true,
    showUpload: true,
    showRemove: true,
    showClose: true,
    browseClass: 'btn btn-primary',
    uploadAsync: true,
    enctype: 'multipart/form-data'
});

$('#find_address_by_name1').click(function (e) {
    let addressName = $('#rent_address_find').val();
    $('#noAddress1').empty();
    $.ajax({
        type: 'get',
        url: 'http://127.0.0.1:8086/sharedcar/simple/address',
        data: {addressName: addressName},
        dataType: 'json',
        success: (result) => {
            $('#rent_address').empty();
            $(result).each((i, address) => {
                let text = `<option value="${address.id}">${address.addressName}</option>`
                $('#rent_address').append(text);
            })
            if (!$('#rent_address').html()) {
                $('#noAddress1').text("暂时没有您搜索的相关取车地点");
                $('#cars').empty();
            } else {
                findCar($('#rent_address'))
            }
        }
    })
})

$('#find_address_by_name2').click(function (e) {
    let addressName = $('#return_address_find').val();
    $('#noAddress2').empty();
    $.ajax({
        type: 'get',
        url: 'http://127.0.0.1:8086/sharedcar/simple/address',
        data: {addressName: addressName},
        dataType: 'json',
        success: (result) => {
            $('#return_address').empty();
            $(result).each((i, address) => {
                let text = `<option value="${address.id}">${address.addressName}</option>`
                $('#return_address').append(text);
            })
            if (!$('#return_address').html()) {
                $('#noAddress2').text("暂时没有您搜索的相关还车地点");
            }
            checkSpace($('#return_address'))
        }
    })
})

$('#rentCar').click(function () {
    $('#noComplete').empty();
    if (!$('#noAddress1').html() && !$('#noAddress2').html() && !$('#noCar').html() &&
        $('#return_address').val() != null && $('#cars').val() != null && $('#rent_address').val() != null) {
        let userId = JSON.parse(window.sessionStorage.getItem("user")).id;
        let carId = $('#cars').val();
        let addressId = $('#rent_address').val();
        let data = {userId: userId, carId: carId, type: '租赁', addressId: addressId}
        $.ajax({
            type: 'post',
            url: 'http://127.0.0.1:8086/sharedcar/complex/business',
            data: JSON.stringify(data),
            contentType: 'application/json',
            dataType: 'json',
            success: (result) => {
                if (result.code == 200) {
                    if (result.message == '成功') {
                        window.alert("租车成功!请在15分钟内前往取车点取车");
                    } else {
                        window.alert(result.message)
                    }
                }
            }
        })
    } else {
        $('#noComplete').text("信息有误,请填写完整");
    }
})

$('#returnCar').click(function () {
    $('#noComplete').empty();
    if (!$('#noAddress2').html() &&
        $('#return_address').val() != null && $('#mile').val() != null && $('#energy_cost').val() != null) {
        let userId = JSON.parse(window.sessionStorage.getItem("user")).id;
        let addressId = $('#return_address').val();
        let mile = $('#mile').val();
        let energyCost = $('#energy_cost').val();
        let data = {userId: userId, addressId: addressId, type: '归还', mile: mile, energyCost: energyCost}
        $.ajax({
            type: 'post',
            url: 'http://127.0.0.1:8086/sharedcar/complex/business',
            data: JSON.stringify(data),
            contentType: 'application/json',
            dataType: 'json',
            success: (result) => {
                if (result.code == 200) {
                    if (result.message == '成功') {
                        window.alert("归还成功!")
                    } else {
                        window.alert(result.message)
                    }
                }
            }
        })
    } else {
        $('#noComplete').text("信息有误,请填写完整");
    }
})

function findCar(s) {
    let addressId = $(s).val();
    $('#noCar').empty();
    $.ajax({
        type: 'get',
        url: 'http://127.0.0.1:8086/sharedcar/simple/car',
        data: {addressId: addressId},
        dataType: 'json',
        success: (result) => {
            $('#cars').empty();
            $(result).each((i, car) => {
                let text = ``;
                if ("正在使用" == car.carCondition)
                    text += `<option value="${car.id}" disabled>${car.carNumber}使用中</option>`;
                else
                    text += `<option value="${car.id}">${car.carNumber}</option>`;
                $('#cars').append(text);
            })
            if (!$('#cars').html()) {
                $('#noCar').text("您选择的取车点暂时没有可用车辆");
            }
        }
    })
}

function checkSpace(s) {
    let returnAddress = $(s).val();
    $('#noAddress2').empty();
    $.ajax({
        type: 'get',
        url: 'http://127.0.0.1:8086/sharedcar/simple/address',
        data: {id: returnAddress},
        dataType: 'json',
        success: (result) => {
            if (result[0].max <= result[0].current) {
                $('#noAddress2').text("您选择的还车点已没有停车位")
            }
        }
    })
}