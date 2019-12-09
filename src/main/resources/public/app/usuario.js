/* global MESSAGES, axios */

new Vue({
    el: "#usuarioVUE",
    data: {
        usuario: {},
        nombre: 'orlando camavilca '
    },
    methods: {
        registro() {
            const $vue = this;
            axios.post("/registro", $vue.usuario).then(response => {
                if (response.data.success) {
                    var datos = response.data.data;
                    window.location.replace(`/dashboard`);
                    notify2(response.data.message, "success");
                } else {
                    notify2(response.data.message, "error");
                }
            }).catch((err) => {
                notify2(MESSAGES.errorComunicacion, "error");
            });
        },
        iniciar() {
            const $vue = this;
            axios.post("/iniciar", $vue.usuario).then(response => {
                if (response.data.success) {
                    var datos = response.data.data;
                    window.location.replace(`/dashboard`);
                    notify2(response.data.message, "success");
                } else {
                    notify2(response.data.message, "error");
                }
            }).catch((err) => {
                notify2(MESSAGES.errorComunicacion, "error");
            });
        }
    }
});