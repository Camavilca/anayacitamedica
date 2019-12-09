Vue.component("modal", {template: "#modal_template"});
Vue.component('multiselect', {
    mixins: [window.VueMultiselect.default],
    props: {selectLabel: {default: ""}}
});
new Vue({
    el: "#citamedicaVUE",
    data: {
        citamedica: {},
        citamedicas: [],
        medicos: [],
        pacientes: [],
        citamedicaModal: {
            id: "citamedicaModal",
            header: true,
            title: 'Nuevo Cita Medica',
            okbtn: 'Aceptar',
            modalSize: 'modal-lg',
            processing: false
        },
        allURL: APP.url('citamedica/allDynatable')
    },
    mounted() {
        const $vue = this;
        $vue.$refs.load.repreload();
        $vue.allPacientes();
        $vue.allMedicos();
    },
    methods: {
        openModalCitaMedica() {
            let $vue = this;
            $vue.citamedica = {};
            $vue.$refs.citamedicaModal.open();
        },
        save() {
            let $vue = this;
            axios.post("/citamedica/save", $vue.citamedica).then(response => {
                if (response.data.success) {
                    $vue.$refs.load.repreload();
                    $vue.$refs.citamedicaModal.close();
                    notify2(response.data.message, "success");
                } else {
                    notify2(response.data.message, "error");
                }
            }).catch((err) => {
                notify2(MESSAGES.errorComunicacion, "error");
            });
        },
        eliminar(citamedica) {
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
                    axios.post("/citamedica/delete", citamedica).then(response => {
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
        update(citamedica) {
            const $vue = this;
            $vue.citamedicaModal.title = `Editar`;
            $vue.openModalCitaMedica();
            $vue.citamedica = citamedica;
        },
        allMedicos() {
            let $vue = this;
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
        allPacientes() {
            let $vue = this;
            axios.get("/paciente/all").then(response => {
                if (response.data.success) {
                    $vue.pacientes = response.data.data;
                } else {
                    notify2(response.data.message, "error");
                }
            }).catch((err) => {
                notify2(MESSAGES.errorComunicacion, "error");
            });
        }
    }
});