$(function () {
    $('input').attr('autocomplete', 'off');

    $('.scrollable').scroll(function () {

        var limit = ($('#profileContainer').length === 1) ? 230 : 0;
        var nav = $('.subbar');

        if ($(this).scrollTop() > limit) {

            nav.addClass("f-nav");
        } else {

            nav.removeClass("f-nav");
        }
    });


    var alto = $('.main-sidebar').height() - 100;
    $('.contenedor-secciones').height(alto);

    $(window).resize(function () {
        var alto = $('.main-sidebar').height() - 100;
        $('.contenedor-secciones').height(alto);
    });

    $("body").delegate(".exit-session", "click", function () {
        var win = window.open('http://www.google.com.mx/accounts/Logout2', '_blank', 'modal=yes,width=500,height=500');
        location.href = "/logout";
    });
});

Messenger.options = {
    extraClasses: 'messenger-fixed messenger-on-bottom messenger-on-right',
    theme: 'flat'
}

function notify(message, type) {
    var t = (type == null) ? 'success' : type;
    setTimeout(function () {
        Messenger().post({
            message: message,
            type: t,
            hideAfter: 12,
            showCloseButton: true
        });
    }, 900);
}


iziToast.settings({
    timeout: 5000,
    resetOnHover: true,
    transitionIn: 'flipInX',
    transitionOut: 'flipOutX',
    displayMode: 2
});

function notify2(message, type, tit, ic) {
    var t = (type == null) ? 'default' : type;
    var title = (tit == null) ? '' : tit;
    var typeO = this.MESSAGETYPE[t];
    var icon = (ic == null) ? typeO.icon : ic;
    message = message == null ? 'null' : message;
    setTimeout(function () {
        iziToast.show({
            title: title,
            message: message,
            icon: icon,
            color: typeO.color,
        });
    }, 500);
}

MESSAGETYPE = {
    success: {color: 'green', icon: 'fa fa-check'},
    info: {color: 'blue', icon: 'fa fa-info'},
    warning: {color: 'yellow', icon: 'fa fa-exclamation-triangle'},
    error: {color: 'red', icon: 'fa fa-ban'},
    default: {color: '', icon: 'fa fa-comment-alt'}
}


function randString(n) {
    if (!n) {
        n = 5;
    }

    var text = '';
    var possible = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    for (var i = 0; i < n; i++) {
        text += possible.charAt(Math.floor(Math.random() * possible.length));
    }

    return text;
}

//$.fn.datepicker.defaults.format = "dd/mm/yyyy";
//$.fn.datepicker.defaults.language = "es";
//$.fn.datepicker.defaults.autoclose = true;
//$.fn.datepicker.defaults.todayHighlight = false;
//$.fn.datepicker.defaults.showButtonPanel = false;

Sidebar = {
    position: function (str, m, i) {
        return str.split(m, i).join(m).length;
    },
    initialize: function () {
        var t = $(".nav-primary"),
                i = window.location.pathname,
                u = i.substr(0, Sidebar.position(i, '/', 3));
        t.find(".nav li").removeClass("active");
        var s = t.find("a[href='" + u + "']").parent();
        if (s.length) {
            if (s.addClass("active"), s.parents(".nav").length) {
                s.parents(".nav").parent().addClass("active")
            }
        } else {
            t.find(".nav li:eq(0)").addClass("active");
        }
    }
};

//Sidebar.initialize();

Topbar = {
    position: function (str, m, i) {
        return str.split(m, i).join(m).length;
    },
    initialize: function () {
        var t = $(".nav-topbar"),
                i = window.location.pathname;

        var u = i.substr(0, Sidebar.position(i, '/', 4));
        if ((i.match(/\//g) || []).length > 5) {
            u = i.substr(0, Sidebar.position(i, '/', 5));
        }
        var s = t.find("a[href='" + u + "']").parent();

        if (s.length) {
            if (s.addClass("active"), s.parents(".nav").length) {
                s.parents("li").addClass("active");
            }
        } else {
            t.find(".nav li:eq(0)").addClass("active");
        }
    }
};

//Topbar.initialize();

MODAL = {
    idModalMd: "modalVik",
    idModalLg: "modalVikLarge",
    idModalSm: "modalVikSmall",
    modalActivo: null,
    idContent: "contentModalVik",
    idTitle: "titleModalVik",
    idBody: "bodyModalVik",
    idFooter: "footerModalVik",
    idBtnClose: "btnCerrarModalVik",
    idDivButtons: "buttonsModalVik",
    textButtonAffected: "",
    textModalWait: "",
    buttonAffected: null,
    init: function (type) {
        if (type == "sm") {
            MODAL.modalActivo = $("#" + MODAL.idModalSm);
        } else if (type == "lg") {
            MODAL.modalActivo = $("#" + MODAL.idModalLg);
        } else if (type == "md") {
            MODAL.modalActivo = $("#" + MODAL.idModalMd);
        }
    },
    modalDefault: function () {
        if (MODAL.modalActivo == null) {
            MODAL.modalActivo = $("#" + MODAL.idModalMd);
        }
    },
    title: function (html) {
        MODAL.modalDefault();
        MODAL.modalActivo.find("#" + MODAL.idTitle).html(html);
    },
    buttons: function (html) {
        MODAL.modalDefault();
        MODAL.modalActivo.find("#" + MODAL.idDivButtons).html(html);
    },
    body: function (html) {
        MODAL.modalDefault();
        if (html == null) {
            return MODAL.modalActivo.find("#" + MODAL.idBody);
        }
        MODAL.modalActivo.find("#" + MODAL.idBody).html(html);
    },
    getBody: function () {
        MODAL.modalDefault();
        return MODAL.modalActivo.find("#" + MODAL.idBody);
    },
    getFooter: function () {
        MODAL.modalDefault();
        return MODAL.modalActivo.find("#" + MODAL.idFooter);
    },
    footer: function () {
        MODAL.modalDefault();
        return MODAL.modalActivo.find("#" + MODAL.idFooter);
    },
    show: function () {
        MODAL.modalDefault();
        MODAL.modalActivo.modal();
    },
    invisible: function () {
        MODAL.modalDefault();
        MODAL.modalActivo.modal("hide");
    },
    hide: function () {
        MODAL.modalDefault();
        MODAL.activateButtons();
        MODAL.modalActivo.modal("hide");
        MODAL.limpiar(MODAL.modalActivo);
    },
    limpiar: function (modal) {
        MODAL.modalDefault();
        modal.find("#" + MODAL.idTitle).html("");
        modal.find("#" + MODAL.idBody).html("");
        modal.find("#" + MODAL.idDivButtons).html("");
    },
    disableButtons: function (btn, htmlBtn) {
        MODAL.modalActivo.find("button").each(function (i, item) {
            $(item).attr("disabled", "disabled");
        });
        MODAL.modalActivo.find("a").each(function (i, item) {
            $(item).attr("disabled", "disabled");
        });
        if (btn != null) {
            MODAL.buttonAffected = btn;
            if (htmlBtn != null) {
                MODAL.textButtonAffected = btn.html();
                btn.html(htmlBtn);
            } else {
                MODAL.textButtonAffected = btn.html();
                btn.html('<i class="fa fa-spinner fa-spin fa-lg"></i> Procesando');
            }
        }
    },
    activateButtons: function (btn, htmlBtn) {
        setTimeout(function () {
            var footer = MODAL.modalActivo.find("#" + MODAL.idFooter);
            footer.find("button").each(function (i, item) {
                $(item).removeAttr("disabled");
            });
            footer.find("a").each(function (i, item) {
                $(item).removeAttr("disabled");
            });
            if (btn != null) {
                btn.html(htmlBtn);
                return;
            }
            if (MODAL.buttonAffected != null && htmlBtn != null) {
                MODAL.buttonAffected.html(htmlBtn);
                return;
            }
            if (MODAL.buttonAffected != null) {
                MODAL.buttonAffected.html(MODAL.textButtonAffected);
            }
        }, 800);
    },
    activateButtonsInmediate: function (btn, htmlBtn) {
        var footer = MODAL.modalActivo.find("#" + MODAL.idFooter);
        footer.find("button").each(function (i, item) {
            $(item).removeAttr("disabled");
        });
        footer.find("a").each(function (i, item) {
            $(item).removeAttr("disabled");
        });
        if (btn != null) {
            btn.html(htmlBtn);
            return;
        }
        if (MODAL.buttonAffected != null && htmlBtn != null) {
            MODAL.buttonAffected.html(htmlBtn);
            return;
        }
        if (MODAL.buttonAffected != null) {
            MODAL.buttonAffected.html(MODAL.textButtonAffected);
        }
    },
    showWait: function (msg) {
        if (msg != null) {
            MODAL.textModalWait = $("#bodyModalWait").html();
            $("#bodyModalWait").html(msg);
        }
        $("#modalWait").modal();
    },
    hideWait: function () {
        setTimeout(function () {
            $("#bodyModalWait").html(MODAL.textModalWait);
            $("#modalWait").modal('hide');
        }, 1000);
    }
};

APP = {
    colorEstado: {CRE: "default",
        ACT: "success", MAT: "success",
        ANU: "danger", BLO: "danger", INA: "danger", RHZ: "danger", RCU: "danger", RCI: "danger",
        APR: "primary", ACEP: "primary",
        OBS: "warning",
        SOL: "info", REE: "info"},
    getEstadoClass: function (estadoCode) {
        return "label-" + APP.colorEstado[estadoCode];
    },
    cleanForm: function (f) {
        f.find("input[type=text], textarea, #id").val("");
        f.find("input[type=checkbox]").prop("checked", false);
        f.find("input[name='*[]']").prop("checked", false);
        f.find("input[name='id*']").val("");
    },
    cleanAll: function (f) {
        f.find("input, textarea").val("");
    },
    fillFormById: function (data, f) {
        $.each(data, function (index, value) {
            f.find('#' + index).val(value);
        });
    },
    fillFormByName: function (data, f) {
        $.each(data, function (index, value) {
            f.find('[name="' + index + '"]').val(value);
        });
    },
//    select2: function () {
//        $(".select2single").select2({minimumResultsForSearch: -1});
//        $(".select2").select2();
//    },
    url: function (relative) {
        return contextPath + relative;
    },
    timePicker: {
        minuteStep: 5,
        showInputs: true,
        disableFocus: true,
        showSeconds: false,
        showMeridian: false,
    },
    disableButtonsModal: function (idFooter, btn, htmlBtn) {
        $("#" + idFooter).find("button").each(function (i, item) {
            $(item).attr("disabled", "disabled");
        });
        $("#" + idFooter).find("a").each(function (i, item) {
            $(item).attr("disabled", "disabled");
        });
        if (btn != null) {
            if (htmlBtn != null) {
                btn.html(htmlBtn);
            } else {
                btn.html('<i class="fa fa-spinner fa-spin fa-lg"></i> Procesando');
            }
        }
    },
    activatedButtonsModal: function (idFooter, btn, htmlBtn) {
        setTimeout(function () {
            $("#" + idFooter).find("button").each(function (i, item) {
                $(item).removeAttr("disabled");
            });
            $("#" + idFooter).find("a").each(function (i, item) {
                $(item).removeAttr("disabled");
            });
            if (btn != null) {
                btn.html(htmlBtn);
            }
        }, 1000);
    },
    disableButtonsModalVik: function (btn, htmlBtn) {
        $("#footerModalVik").find("button").each(function (i, item) {
            $(item).attr("disabled", "disabled");
        });
        $("#footerModalVik").find("a").each(function (i, item) {
            $(item).attr("disabled", "disabled");
        });
        if (btn != null) {
            if (htmlBtn != null) {
                btn.html(htmlBtn);
            } else {
                btn.html('<i class="fa fa-spinner fa-spin fa-lg"></i> Procesando');
            }
        }
    },
    activatedButtonsModalVik: function (btn, htmlBtn) {
        setTimeout(function () {
            $("#footerModalVik").find("button").each(function (i, item) {
                $(item).removeAttr("disabled");
            });
            $("#footerModalVik").find("a").each(function (i, item) {
                $(item).removeAttr("disabled");
            });
            if (btn != null) {
                btn.html(htmlBtn);
            }
        }, 1000);
    },
    activatedRapidoButtonsModalVik: function (btn, htmlBtn) {
        $("#footerModalVik").find("button").each(function (i, item) {
            $(item).removeAttr("disabled");
        });
        $("#footerModalVik").find("a").each(function (i, item) {
            $(item).removeAttr("disabled");
        });
        if (btn != null) {
            btn.html(htmlBtn);
        }
    },
    verModalWait: function (msg) {
        if (msg != null) {
            $("#bodyModalWait").html(msg);
        }
        $("#modalWait").modal();
    },
    cerrarModalWait: function () {
        setTimeout(function () {
            $("#modalWait").modal('hide');
        }, 1000);
    },
    cerrarRapidoModalWait: function () {
        $("#modalWait").modal('hide');
    },
    limpiarRaros: function ($this) {
        var conte = $this.val();
        conte = conte.replace(/[\n\f\b\r\t]/g, '');
        $this.val(conte);
    },
    revisarNombre: function ($this) {
        var nom = $this.val().toLowerCase().replace(/[^a-zçñáéíóúü\s'\-]/g, '');
        nom = nom.replace(/[\n\f\b\r|,\t]/g, ' ').replace(/\s\s+/g, ' ').trim();
        nom = APP.capitalize(nom, " ");
        nom = APP.capitalize(nom, "'");
        nom = APP.capitalize(nom, "-");

        $this.val(nom);
    },
    capitalize: function (string, separator) {
        var arr = string.split(separator);
        $.each(arr, function (i, value) {
            arr[i] = value.charAt(0).toUpperCase() + value.substr(1);
        });
        return arr.join(separator);
    },
    eliminarEspacios: function ($this) {
        var conte = $this.val().replace(/[\s\n\f\b\r\t]/g, '');
        $this.val(conte);
    },
    revisarDireccion: function ($this) {
        var conte = $this.val().replace(/[\n\f\b\r\t|]/g, ' ').replace(/\s\s+/g, ' ').trim();
        $this.val(conte);
    },
    revisarEmail: function ($this) {
        var conte = $this.val().toLowerCase().replace(/[\n\f\b\r\t\s|,'"!$%&/]/g, '').trim();
        conte = APP.stripAccents(conte);
        $this.val(conte);
    },
    stripAccents: function (str) {
        var from = "àáäâèéëêìíïîòóöôùúüûñç";
        var to = "aaaaeeeeiiiioooouuuunc";
        for (var i = 0, l = from.length; i < l; i++) {
            str = str.replace(new RegExp(from.charAt(i), 'g'), to.charAt(i));
        }
        return str;
    }
};

MESSAGES = {
    errorComunicacion: 'Error de conexión con el servidor.',
    confirmDelete: '¿Seguro que desea eliminar el registro?',
    confirmActive: '¿Seguro que desea activar el registro?',
    confirmDesActive: '¿Seguro que desea desactivar el registro?'
};


//APP.select2();

$.fn.disableButtonsModal = function (btn, htmlBtn)
{
    var el = this;

    $(el).find(".modal-footer>button").each(function (i, item) {
        $(item).attr("disabled", "disabled");
    });
    $(el).find(".modal-footer>a").each(function (i, item) {
        $(item).attr("disabled", "disabled");
    });
    if (btn != null) {
        if (htmlBtn != null) {
            btn.html(htmlBtn);
        } else {
            btn.html('<i class="fa fa-spinner fa-spin fa-lg"></i> Procesando');
        }
    }
    return el;
};

$.fn.enableButtonsModal = function (btn, htmlBtn)
{
    var el = this;

    setTimeout(function () {

        $(el).find(".modal-footer>button").each(function (i, item) {
            $(item).removeAttr("disabled");
        });

        $(el).find(".modal-footer>a").each(function (i, item) {
            $(item).removeAttr("disabled");
        });

        if (btn != null) {
            btn.html(htmlBtn);
        }

    }, 1000);

    return el;
};

$.fn.cleanForm = function ()
{
    $(this).find("input[type=text], textarea, #id, input[name='id'] ").val("");
    $(this).find("input[type=checkbox]").prop("checked", false);
    $(this).find("input[type=radio]").prop("checked", false);
    $(this).find("input[name='*[]']").prop("checked", false);
    $(this).find("input[name='id*']").val("");
    return this;
};

$.fn.cleanAll = function ()
{
    $(this).find("input, textarea").val("");
    return this;
};




/* wizard */


$(document).ready(function () {
    //Initialize tooltips
    $('.nav-tabs > li a[title]').tooltip();

    //Wizard
    $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {

        var $target = $(e.target);

        if ($target.parent().hasClass('disabled')) {
            return false;
        }
    });

    $(".next-step").click(function (e) {

        var $active = $('.wizard .nav-tabs li.active');
        $active.next().removeClass('disabled');
        nextTab($active);

    });
    $(".prev-step").click(function (e) {

        var $active = $('.wizard .nav-tabs li.active');
        prevTab($active);

    });
});

function nextTab(elem) {
    $(elem).next().find('a[data-toggle="tab"]').click();
}
function prevTab(elem) {
    $(elem).prev().find('a[data-toggle="tab"]').click();
}


//according menu

$(document).ready(function ()
{
    //Add Inactive Class To All Accordion Headers
    $('.accordion-header').toggleClass('inactive-header');

    //Set The Accordion Content Width
    var contentwidth = $('.accordion-header').width();
    $('.accordion-content').css({});

    //Open The First Accordion Section When Page Loads
    $('.accordion-header').first().toggleClass('active-header').toggleClass('inactive-header');
    $('.accordion-content').first().slideDown().toggleClass('open-content');

    // The Accordion Effect
    $('.accordion-header').click(function () {
        if ($(this).is('.inactive-header')) {
            $('.active-header').toggleClass('active-header').toggleClass('inactive-header').next().slideToggle().toggleClass('open-content');
            $(this).toggleClass('active-header').toggleClass('inactive-header');
            $(this).next().slideToggle().toggleClass('open-content');
        } else {
            $(this).toggleClass('active-header').toggleClass('inactive-header');
            $(this).next().slideToggle().toggleClass('open-content');
        }
    });

    return false;
});

var $global = new Vue();


const AXIOS = axios.create({});

AXIOS.interceptors.response.use(function (response) {
    if (!response.data.success) {
        notify(response.data.message, 'error');
    } else {
        if (response.data.message) {
            notify(response.data.message, 'info');
        }
    }
    return response;
}, function (error) {
    notify(MESSAGES.errorComunicacion, 'error');
    return Promise.reject(error);
});



var VueLoader = {
    methods: {
        showLoader(title) {
            if (title == null) {
                $('body').loadingModal({text: ''});
                $('body').loadingModal('animation', 'cubeGrid');
            } else {
                $('body').loadingModal({text: title});
                $('body').loadingModal('animation', 'cubeGrid');
            }
        },
        hideLoader() {
            setTimeout(function () {
                $('body').loadingModal('hide');
                setTimeout(function () {
                    $('body').loadingModal('destroy');
                }, 1000);
            }, 1000);
        }
    }
}
/**
 *
 *  Base64 encode / decode
 *  http://www.webtoolkit.info/
 *
 **/
var Base64 = {

// private property
    _keyStr: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",

// public method for encoding
    encode: function (input) {
        var output = "";
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        var i = 0;

        input = Base64._utf8_encode(input);

        while (i < input.length) {

            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);

            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;

            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }

            output = output +
                    this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
                    this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);

        }

        return output;
    },

// public method for decoding
    decode: function (input) {
        var output = "";
        var chr1, chr2, chr3;
        var enc1, enc2, enc3, enc4;
        var i = 0;

        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

        while (i < input.length) {

            enc1 = this._keyStr.indexOf(input.charAt(i++));
            enc2 = this._keyStr.indexOf(input.charAt(i++));
            enc3 = this._keyStr.indexOf(input.charAt(i++));
            enc4 = this._keyStr.indexOf(input.charAt(i++));

            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;

            output = output + String.fromCharCode(chr1);

            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }

        }

        output = Base64._utf8_decode(output);

        return output;

    },

// private method for UTF-8 encoding
    _utf8_encode: function (string) {
        string = string.replace(/\r\n/g, "\n");
        var utftext = "";

        for (var n = 0; n < string.length; n++) {

            var c = string.charCodeAt(n);

            if (c < 128) {
                utftext += String.fromCharCode(c);
            } else if ((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            } else {
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }

        }

        return utftext;
    },

// private method for UTF-8 decoding
    _utf8_decode: function (utftext) {
        var string = "";
        var i = 0;
        var c = c1 = c2 = 0;

        while (i < utftext.length) {

            c = utftext.charCodeAt(i);

            if (c < 128) {
                string += String.fromCharCode(c);
                i++;
            } else if ((c > 191) && (c < 224)) {
                c2 = utftext.charCodeAt(i + 1);
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                i += 2;
            } else {
                c2 = utftext.charCodeAt(i + 1);
                c3 = utftext.charCodeAt(i + 2);
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                i += 3;
            }

        }

        return string;
    }

}
