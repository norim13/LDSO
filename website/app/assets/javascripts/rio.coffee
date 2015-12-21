# Place all the behaviors and hooks related to the matching controller here.
# All this logic will automatically be available in application.js.
# You can use CoffeeScript in this file: http://coffeescript.org/
`
//var base_url = 'http://***REMOVED***:10500/geoserver/rios';
var base_url = '***REMOVED***/geoserver/rios';
currentMarker = null;
cod_rio = null;
//lat_deg = 110.574;//Latitude: 1 deg = 110.574 km
//lon_deg = 111.320*cos(latitude);//Longitude: 1 deg = 111.320*cos(latitude) km
//http://stackoverflow.com/questions/1253499/simple-calculations-for-working-with-lat-lon-km-distance
$(document).ready(function(){

    //$('.datepicker').datepicker();
    //INFO RIO
    cod_rio = $("#cod-rio").html();

    var filter2 = new OpenLayers.Filter.Comparison({
        type: OpenLayers.Filter.Comparison.LIKE,
        matchCase:false,
        property: "EU_CD",
        value: cod_rio
    });

    var  wfsProtocol2 = new OpenLayers.Protocol.WFS.v1_1_0({
        url: base_url+'/wms?SERVICE=WFS',
        geometryName: "SHAPE",
        featurePrefix: 'rios',
        //featureType: 'AtAgua_Agsup_rios_AAmb_SNIRH_PC',
        featureType: 'BaciasMA',
        srsName: 'EPSG:900913' // eg "EPSG:900913"
    });

    wfsProtocol2.read ({
        filter:filter2,
        callback: fillInfoRio
        //scope: strategy
    });

    function fillInfoRio(request) {
        //console.log(request);
        // the first bit sets the value of sExt to be the a boundingbox on the features returned.
        // Depending on application logic, this could happen in other places in the code.
        if(request.features.length == 0){
            $("#designacao-rio").val("Código inválido...");
        }
        else {
            //console.log(request.features[0].data);
            var info_rio = request.features[0].data;
            /*$("#designacao-rio").html(decode_utf8(info_rio.designacao));
            $("#tipo-rio").html(decode_utf8(info_rio.tipo));
            $("#bacia-rio").html(decode_utf8(info_rio.bacia));

            $('#perfil-rio-title').html("Informações sobre "+decode_utf8(info_rio.designacao));*/

            $("#designacao-rio").html(info_rio.RIVER_NAME);
            $("#tipo-rio").html(info_rio.TYPE_NAME);
            $('#perfil-rio-title').html("Informações sobre "+info_rio.RIVER_NAME);
            $('#tipologia-rio').html(info_rio.TYPE_NAME);
            /*getRiosPais(cod_rio);
            getAfluentes(cod_rio);*/
        }
    }

    function getRiosPais(codigo){
        var components = codigo.split(".");
        if(components.length > 0){

            components.splice(components.length - 1, 1); //remove ultimo elemento (é o próprio rio)

            var id_rio_temp = "";
            for(var i in components){
                id_rio_temp = id_rio_temp+components[i];
                $("#rios-pais").append('<tr id="rios-pais-'+id_rio_temp.replace(/\./g,'-')+'"></tr>');
                //console.log(id_rio_temp);
                var filter_pai = new OpenLayers.Filter.Comparison({
                    type: OpenLayers.Filter.Comparison.LIKE,
                    matchCase:false,
                    property: "codrios",
                    value: id_rio_temp
                });

                wfsProtocol2.read ({
                    filter:filter_pai,
                    callback: function(request){
                        //console.log(request.features[0]);
                        var rio = [];
                        if(request.features[0].length != 0) {//se recebeu dados
                            var rio_data = request.features[0].data;
                            rio['cod_rio'] = decode_utf8(rio_data.codrios);
                            rio['designacao'] = decode_utf8(rio_data.designacao);
                            rio['bacia'] = decode_utf8(rio_data.bacia);
                            rio['tipo'] = decode_utf8(rio_data.tipo);
                        }
                        else{ //não recebeu dados
                            rio['cod_rio'] = id_rio_temp;
                            rio['designacao'] = "Erro ao obter informação...";
                            rio['bacia'] = "";
                            rio['tipo'] = "";
                        }
                        //adiciona dados à tabela
                        var selector = "#rios-pais-"+rio['cod_rio'].replace(/\./g,'-');
                        //console.log(selector);
                        $(selector).html('<td>'+rio['cod_rio']+'</td>'
                            +'<td>'+rio['designacao']+'</td>'
                            +'<td>'+rio['tipo']+'</td>'
                            +'<td>'+rio['bacia']+'</td>'
                            +'<td><a href="/rio/'+rio['cod_rio']+'">'+
                                '<button class="btn btn-primary">Ver Rio</button>'+'</a></td>');
                    }
                });
                id_rio_temp = id_rio_temp+".";
            }
        }
    }

    function getAfluentes(codigo){

        var filter_afluentes = new OpenLayers.Filter.Comparison({
            type: OpenLayers.Filter.Comparison.LIKE,
            matchCase:false,
            property: "codrios",
            value: codigo+".*"
        });

        wfsProtocol2.read ({
            filter:filter_afluentes,
            callback: function(request){
                //console.log(request);
                var rios = [];
                for(var i in request.features){
                    var rio = [];
                    var rio_data = request.features[i].data;
                    rio['cod_rio'] = decode_utf8(rio_data.codrios);
                    if (rio['cod_rio'] == codigo)
                        continue;
                    rio['designacao'] = decode_utf8(rio_data.designacao);
                    rio['bacia'] = decode_utf8(rio_data.bacia);
                    rio['tipo'] = decode_utf8(rio_data.tipo);
                    rios.push(rio);
                }

                //ordenar os rios pelo seu código
                rios.sort(function(a, b){
                    if(a['cod_rio'] < b['cod_rio']) return -1;
                    if(a['cod_rio'] > b['cod_rio']) return 1;
                    return 0;
                })

                //adiciona dados à tabela
                for(var i in rios){
                    $("#rios-afluentes tbody").append('<tr><td>'+rios[i]['cod_rio']+'</td>'
                        +'<td>'+rios[i]['designacao']+'</td>'
                        +'<td>'+rios[i]['tipo']+'</td>'
                        +'<td>'+rios[i]['bacia']+'</td>'
                        +'<td><a href="/rio/'+rios[i]['cod_rio']+'">'+
                        '<button class="btn btn-primary">Ver Rio</button>'+'</a></td></tr>');
                }

                $("#rios-afluentes").after('<div class="col-md-12 text-center">'+
                    '<ul class="pagination" id="myPager"></ul>'+
                    '</div>');

                $('#rios-afluentes tbody').pageMe({
                    pagerSelector:'#myPager',
                    showPrevNext:true,
                    hidePageNumbers:false,
                    perPage:10
                });
            }
        });
    }

    drawChart(null);


    // MAP
    var mapCanvas = document.getElementById('profile-rio-map');
    var mapOptions = {
        center: new google.maps.LatLng(41.179379, -8.606543),
        zoom: 15,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    map = new google.maps.Map(mapCanvas, mapOptions);

    $("#current-location-map-btn").on('click', addGPSMarker);
    addClickListenerToMap();

});

function convertDataForChart(data_array){
    var ret = [];
    for(var i in data_array){
        switch(data_array[i]){
            case 1: ret[i] = 5; break;
            case 2: ret[i] = 4; break;
            case 3: ret[i] = 3; break;
            case 4: ret[i] = 2; break;
            case 5: ret[i] = 1; break;
            default: ret[i] = 0 ; break;
        }
    }
    return ret;
}

/**
 *  if data = null, vai buscar os valores IRR globais do rio. senao recebe a resposta da query ao controller
 */
function drawChart(data){
    // CHART IRR
    if ($("#chart-irr").length){
        ctx = $("#chart-irr").get(0).getContext("2d");
        // This will get the first returned node in the jQuery collection.
        var irr_hidrogeomorfologia = null;
        var irr_qualidadedaagua = null;
        var irr_alteracoesantropicas = null;
        var irr_corredorecologico = null;
        var irr_participacaopublica = null;
        var irr_organizacaoeplaneamento = null;
        var irr_total = null;

        var data_set = [];

        if (data == null){
            var temp = [];
            temp.push(parseInt($('#irr_hidrogeomorfologia').html()));
            temp.push(parseInt($('#irr_qualidadedaagua').html()));
            temp.push(parseInt($('#irr_alteracoesantropicas').html()));
            temp.push(parseInt($('#irr_corredorecologico').html()));
            temp.push(parseInt($('#irr_participacaopublica').html()));
            temp.push(parseInt($('#irr_organizacaoeplaneamento').html()));
            temp.push(parseInt($('#irr_total').html()));
            temp = convertDataForChart(temp);
            data_set.push(temp);
        }
        else{
            for(var i in data['forms']){
                var temp = [];
                temp.push(data['forms'][i]['irr_hidrogeomorfologia']);
                temp.push(data['forms'][i]['irr_qualidadedaagua']);
                temp.push(data['forms'][i]['irr_alteracoesantropicas']);
                temp.push(data['forms'][i]['irr_corredorecologico']);
                temp.push(data['forms'][i]['irr_participacaopublica']);
                temp.push(data['forms'][i]['irr_organizacaoeplaneamento']);
                temp.push(data['forms'][i]['irr']);
                temp = convertDataForChart(temp);
                data_set.push(temp);
            }
            var temp = [];
            temp.push(data['media']['irr_hidrogeomorfologia']);
            temp.push(data['media']['irr_qualidadedaagua']);
            temp.push(data['media']['irr_alteracoesantropicas']);
            temp.push(data['media']['irr_corredorecologico']);
            temp.push(data['media']['irr_participacaopublica']);
            temp.push(data['media']['irr_organizacaoeplaneamento']);
            temp.push(data['media']['irr']);
            temp = convertDataForChart(temp);
            data_set.push(temp);

        }

        var data_sets_for_chart = [];
        var colors_transparent = ["rgba(150,243,33,0.2)", "rgba(33,243,150,0.2)", "rgba(243,150,33,0.2)", "rgba(243,33,150,0.2)", "rgba(150,33,243,0.2)", "rgba(33,150,243,0.6)"];
        var colors = ["rgba(150,243,33,1)", "rgba(33,243,150,1)", "rgba(243,150,33,1)", "rgba(243,33,150,1)", "rgba(150,33,243,1)", "rgba(33,150,243,1)"];
        for(var i in data_set)
        {
            var obj = {
                label: "Dataset "+i,
                fillColor: colors_transparent[i],
                strokeColor: colors[i],
                pointColor: colors[i],
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(220,220,220,1)",
                data: data_set[i]
            }
            data_sets_for_chart.push(obj);
        }
        //force last element to have the last color (avg is always blue)
        data_sets_for_chart[data_sets_for_chart.length - 1].fillColor = colors_transparent[colors_transparent.length-1];
        data_sets_for_chart[data_sets_for_chart.length - 1].strokeColor = colors[colors.length-1];
        data_sets_for_chart[data_sets_for_chart.length - 1].pointColor = colors[colors.length-1];


        var data = {
            labels: ["Hidrogeomorfologia", "Alterações Antrópicas", "Corredor Ecológico", "Qualidade da Água", "Participação Pública", "Organização e Planeamento", "Total"],
            datasets: data_sets_for_chart
        };

        var myLineChart = new Chart(ctx).Line(data, {
            bezierCurve: false,
            //scaleBeginAtZero : true,
            scaleOverride:true,
            scaleSteps:5,
            scaleStartValue:0,
            scaleStepWidth:1,
            scaleLabel: function (valuePayload) {
                if(Number(valuePayload.value)===0)
                    return '';
                if(Number(valuePayload.value)===1)
                    return '5';
                if(Number(valuePayload.value)===2)
                    return '4';
                if(Number(valuePayload.value)===3)
                    return '3';
                if(Number(valuePayload.value)===4)
                    return '2';
                if(Number(valuePayload.value)===5)
                    return '1';
            },
            showTooltips: false
            //custom tootip on chart hover
            /*tooltipTemplate: "<%= value %>",
            onAnimationComplete: function()
            {
                //this.showTooltip(this.segments, true);
                //Show tooltips in bar chart (issue: multiple datasets doesnt work http://jsfiddle.net/5gyfykka/14/)
                //this.showTooltip(this.datasets[0].bars, true);
                //Show tooltips in line chart (issue: multiple datasets doesnt work http://jsfiddle.net/5gyfykka/14/)
                //this.showTooltip(this.datasets[0].points, true);
            },
            tooltipEvents: [],*/

        });
    }

}

function addGPSMarker(){
    $("#add-gps-point-trip").attr("disabled", "disabled");
    navigator.geolocation.getCurrentPosition(function(position) {
        var temp_loc = {};
        temp_loc.lat = position.coords.latitude;
        temp_loc.lng = position.coords.longitude;
        placeMarker(temp_loc, map);
    });
};

function addClickListenerToMap(){
    mapClickListenerHandler =
        google.maps.event.addListener(map, 'click', function(event) {
            placeMarker(event.latLng, map);
        });
};

function placeMarker(location, map) {
    if(currentMarker != null) {
        removeCurrentMarker();
    }

    //console.log(location);
    var marker = new google.maps.Marker({
        position: location,
        map: map
    });

    currentMarker = marker;
    google.maps.event.addListener(marker, 'click', function(event) {
        removeCurrentMarker();
    });
    map.panTo(location);

    $("#add-gps-point-trip").removeAttr("disabled");

    requestIRRdata(marker);

};

function removeCurrentMarker() {
    currentMarker.setMap(null);
    currentMarker = null;
};

function requestIRRdata(marker){
    var obj = {};
    console.log(marker);
    obj.lat = marker.position.lat;
    obj.lon = marker.position.lng;
    /*obj.lat = 41.1159570399812;
    obj.lon = -8.5303135134888;*/
    /*obj.lat = 41;
    obj.lon = -8;*/
    obj.raio = 100;
    obj.rio = cod_rio.trim();


    $.ajax({
        type: 'GET',
        url: '/rio/irrrange',
        data: obj,
        success: function(data){
            console.log(data);

            drawChart(data);
        },
        error: function(err){
            console.log("error");
            console.log(err);
        }
    });

};

`