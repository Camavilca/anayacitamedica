Vue.component("modal", {template: "#modal_template"});
Vue.component('multiselect', {
    mixins: [window.VueMultiselect.default],
    props: {selectLabel: {default: ""}}
});
new Vue({
    el: "#medicoVUE",
    data: {
        medico: {},
        area: {},
        medicos: [],
        areas: [],
        medicoModal: {
            id: "medicoModal",
            header: true,
            title: 'Nuevo Medico',
            okbtn: 'Aceptar',
            modalSize: 'modal-lg',
            processing: false
        },
        areaModal: {
            id: "areaModal",
            header: true,
            title: 'Nuevo Area Medica',
            okbtn: 'Continuar',
            btnCancel: false,
            modalSize: 'modal-lg',
            processing: false
        },
        allURL: APP.url('medico/allDynatable')
    },
    mounted() {
        const $vue = this;
        $vue.$refs.load.repreload();
        $vue.allAreaMedica();
        if (localStorage.name) {
            this.name = localStorage.name;
        }
    },
    methods: {
        openModalMedico() {
            let $vue = this;
            $vue.medico = {};
            $vue.$refs.medicoModal.open();
        },
        save() {
            let $vue = this;
            console.log("______________________________");
            console.log($vue.medico);
            console.log("______________________________");
            axios.post("/medico/save", $vue.medico).then(response => {
                if (response.data.success) {
                    $vue.$refs.load.repreload();
                    $vue.$refs.medicoModal.close();
                    notify2(response.data.message, "success");
                } else {
                    notify2(response.data.message, "error");
                }
            }).catch((err) => {
                notify2(MESSAGES.errorComunicacion, "error");
            });
        },
        eliminar(medico) {
            let $vue = this;
            swal.fire({
                title: "¿ Seguro que quieres Eliminar ?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "Aceptar"
            }).then(result => {
                if (result.value) {
                    axios.post("/medico/delete", medico).then(response => {
                        if (response.data.success) {
                            $vue.$refs.load.repreload();
                            notify2(response.data.message, "success");
                        } else {
                            notify2(response.data.message, "error");
                        }
                    }).catch((err) => {
                        notify2(MESSAGES.errorComunicacion, "error");
                    });
                }
            });
        },
        all() {
            const $vue = this;
            axios.get("/medico/all").then(response => {
                if (response.data.success) {
                    $vue.medicos = response.data.data;
                } else {
                    notify2(response.data.message, "error");
                }
            }).catch((err) => {
                notify2(MESSAGES.errorComunicacion, "error");
            });
        },
        update(medico) {
            const $vue = this;
            $vue.medicoModal.title = `Editar ${medico.nombre}`;
            $vue.openModalMedico();
            $vue.medico = medico;
        },
        allAreaMedica() {
            let $vue = this;
            axios.get("/area/all").then(response => {
                if (response.data.success) {
                    $vue.areas = response.data.data;
                } else {
                    notify2(response.data.message, "error");
                }
            }).catch((err) => {
                notify2(MESSAGES.errorComunicacion, "error");
            });
        },
        openModalArea() {
            let $vue = this;
            $vue.area = {};
            $vue.$refs.areaModal.open();
            $vue.$refs.medicoModal.close();
        },
        closeModalArea() {
            let $vue = this;
            $vue.$refs.areaModal.close();
            $vue.$refs.medicoModal.open();
        },
        saveArea() {
            let $vue = this;
            axios.post("/area/save", $vue.area).then(response => {
                if (response.data.success) {
                    $vue.area = {};
                    $vue.allAreaMedica();
                    notify2(response.data.message, "success");
                } else {
                    notify2(response.data.message, "error");
                }
            }).catch((err) => {
                notify2(MESSAGES.errorComunicacion, "error");
            });
        },
        eliminarArea(area) {
            let $vue = this;
            axios.post("/area/delete", area).then(response => {
                if (response.data.success) {
                    $vue.allAreaMedica();
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