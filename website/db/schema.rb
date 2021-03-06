# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20151228015608) do

  # These are extensions that must be enabled in order to support this database
  enable_extension "plpgsql"

  create_table "concelhos", force: :cascade do |t|
    t.datetime "created_at",  null: false
    t.datetime "updated_at",  null: false
    t.integer  "distrito_id"
    t.string   "nome"
  end

  add_index "concelhos", ["distrito_id"], name: "index_concelhos_on_distrito_id", using: :btree

  create_table "distritos", force: :cascade do |t|
    t.string   "nome"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "form_irr_images", force: :cascade do |t|
    t.string   "image"
    t.datetime "created_at",  null: false
    t.datetime "updated_at",  null: false
    t.integer  "form_irr_id"
  end

  add_index "form_irr_images", ["form_irr_id"], name: "index_form_irr_images_on_form_irr_id", using: :btree

  create_table "form_irrs", force: :cascade do |t|
    t.integer  "tipoDeVale"
    t.integer  "perfilDeMargens"
    t.float    "larguraDaSuperficieDaAgua"
    t.float    "profundidadeMedia"
    t.float    "seccao"
    t.float    "velocidadeMedia"
    t.float    "caudal"
    t.boolean  "substratoDasMargens_soloArgiloso"
    t.boolean  "substratoDasMargens_arenoso"
    t.boolean  "substratoDasMargens_pedregoso"
    t.boolean  "substratoDasMargens_rochoso"
    t.boolean  "substratoDasMargens_artificialPedra"
    t.boolean  "substratoDasMargens_artificialBetao"
    t.boolean  "substratoDoLeito_blocoseRocha"
    t.boolean  "substratoDoLeito_calhaus"
    t.boolean  "substratoDoLeito_cascalho"
    t.boolean  "substratoDoLeito_areia"
    t.boolean  "substratoDoLeito_limo"
    t.boolean  "substratoDoLeito_solo"
    t.boolean  "substratoDoLeito_artificial"
    t.boolean  "substratoDoLeito_naoEVisivel"
    t.integer  "estadoGeraldaLinhadeAgua"
    t.float    "pH"
    t.float    "condutividade"
    t.float    "temperatura"
    t.float    "nivelDeOxigenio"
    t.float    "percentagemDeOxigenio"
    t.float    "nitratos"
    t.float    "nitritos"
    t.integer  "transparencia"
    t.boolean  "oleo"
    t.boolean  "espuma"
    t.boolean  "esgotos"
    t.boolean  "impurezas"
    t.boolean  "sacosDePlastico"
    t.boolean  "latas"
    t.string   "indiciosNaAgua_outros"
    t.integer  "corDaAgua"
    t.integer  "odorDaAgua"
    t.boolean  "planarias"
    t.boolean  "hirudineos"
    t.boolean  "oligoquetas"
    t.boolean  "simulideos"
    t.boolean  "quironomideos"
    t.boolean  "ancilideo"
    t.boolean  "limnideo"
    t.boolean  "bivalves"
    t.boolean  "patasNadadoras"
    t.boolean  "pataLocomotoras"
    t.boolean  "trichopteroS"
    t.boolean  "trichopteroC"
    t.boolean  "odonata"
    t.boolean  "heteropteros"
    t.boolean  "plecopteros"
    t.boolean  "baetideo"
    t.boolean  "cabecaPlanar"
    t.boolean  "crustaceos"
    t.boolean  "acaros"
    t.boolean  "pulgaDeAgua"
    t.boolean  "insetos"
    t.boolean  "megalopteres"
    t.boolean  "intervencoes_edificios"
    t.boolean  "intervencoes_pontes"
    t.boolean  "intervencoes_limpezasDasMargens"
    t.boolean  "intervencoes_estabilizacaoDeMargens"
    t.boolean  "intervencoes_modelacaoDeMargensNatural"
    t.boolean  "intervencoes_modelacaoDeMargensArtificial"
    t.boolean  "intervencoes_barragem"
    t.boolean  "intervencoes_diques"
    t.boolean  "intervencoes_rioCanalizado"
    t.boolean  "intervencoes_rioEntubado"
    t.boolean  "intervencoes_esporoes"
    t.boolean  "intervencoes_paredoes"
    t.boolean  "intervencoes_tecnicasDeEngenhariaNatural"
    t.boolean  "ocupacao_florestaNatural"
    t.boolean  "ocupacao_florestaPlantadas"
    t.boolean  "ocupacao_matoAlto"
    t.boolean  "ocupacao_matoRasteiro"
    t.boolean  "ocupacao_pastagem"
    t.boolean  "ocupacao_agricultura"
    t.boolean  "ocupacao_espacoAbandonado"
    t.boolean  "ocupacao_jardins"
    t.boolean  "ocupacao_zonaEdificada"
    t.boolean  "ocupacao_zonaIndustrial"
    t.boolean  "ocupacao_ruas"
    t.boolean  "ocupacao_entulho"
    t.integer  "patrimonio_moinho"
    t.integer  "patrimonio_acude"
    t.integer  "patrimonio_microAcude1"
    t.integer  "patrimonio_microAcude2"
    t.integer  "patrimonio_barragem"
    t.integer  "patrimonio_levadas"
    t.integer  "patrimonio_pesqueiras"
    t.integer  "patrimonio_escadasDePeixe"
    t.integer  "patrimonio_poldras"
    t.integer  "patrimonio_pontesSemPilar"
    t.integer  "patrimonio_pontesComPilar"
    t.integer  "patrimonio_passagemAVau"
    t.integer  "patrimonio_barcos"
    t.integer  "patrimonio_cais"
    t.integer  "patrimonio_igreja"
    t.integer  "patrimonio_solares"
    t.integer  "patrimonio_nucleoHabitacional"
    t.integer  "patrimonio_edificiosParticulares"
    t.integer  "patrimonio_edificiosPublicos"
    t.integer  "patrimonio_ETA"
    t.integer  "patrimonio_descarregadoresDeAguasPluviais"
    t.integer  "patrimonio_coletoresSaneamento"
    t.integer  "patrimonio_defletoresArtificiais"
    t.integer  "patrimonio_motaLateral"
    t.boolean  "salamandraLusitanica"
    t.boolean  "salamandraPintasAmarelas"
    t.boolean  "tritaoVentreLaranja"
    t.boolean  "raIberica"
    t.boolean  "raVerde"
    t.boolean  "sapoComum"
    t.boolean  "lagartoDeAgua"
    t.boolean  "cobraAguaDeColar"
    t.boolean  "cagado"
    t.boolean  "guardaRios"
    t.boolean  "garcaReal"
    t.boolean  "melroDeAgua"
    t.boolean  "galinhaDeAgua"
    t.boolean  "patoReal"
    t.boolean  "tentilhaoComum"
    t.boolean  "chapimReal"
    t.boolean  "lontras"
    t.boolean  "morcegosDeAgua"
    t.boolean  "toupeiraDaAgua"
    t.boolean  "ratoDeAgua"
    t.boolean  "ouricoCacheiro"
    t.boolean  "armilho"
    t.boolean  "enguia"
    t.boolean  "lampreia"
    t.boolean  "salmao"
    t.boolean  "truta"
    t.boolean  "bogaPortuguesa"
    t.boolean  "bogaDoNorte"
    t.boolean  "percaSol"
    t.boolean  "tartarugaDaFlorida"
    t.boolean  "caranguejoPeludoChines"
    t.boolean  "gambusia"
    t.boolean  "mustelaVison"
    t.boolean  "lagostimVermelho"
    t.boolean  "trutaArcoIris"
    t.boolean  "achiga"
    t.boolean  "salgueiral"
    t.boolean  "amial"
    t.boolean  "freixal"
    t.boolean  "choupal"
    t.boolean  "ulmeiral"
    t.boolean  "sanguinos"
    t.boolean  "ladual"
    t.boolean  "tramazeiras"
    t.boolean  "carvalhal"
    t.boolean  "sobreiral"
    t.boolean  "azinhal"
    t.integer  "conservacaoBosqueRibeirinho"
    t.boolean  "silvas"
    t.boolean  "ervaDaFortuna"
    t.boolean  "plumas"
    t.boolean  "lentilhaDaAgua"
    t.boolean  "pinheirinha"
    t.boolean  "jacintoDeAgua"
    t.integer  "obstrucaoDoLeitoMargens"
    t.integer  "disponibilizacaoDeInformacao"
    t.integer  "envolvimentoPublico"
    t.integer  "acao"
    t.integer  "legislacao"
    t.integer  "estrategia"
    t.integer  "gestaoDasIntervencoes"
    t.datetime "created_at",                                null: false
    t.datetime "updated_at",                                null: false
    t.integer  "user_id"
    t.boolean  "erosao_semErosao"
    t.boolean  "erosao_formacaomais3"
    t.boolean  "erosao_formacao1a3"
    t.boolean  "erosao_quedamuros"
    t.boolean  "erosao_rombos"
    t.boolean  "sedimentacao_ausente"
    t.boolean  "sedimentacao_decomposicao"
    t.boolean  "sedimentacao_mouchoes"
    t.boolean  "sedimentacao_ilhassemveg"
    t.boolean  "sedimentacao_ilhascomveg"
    t.boolean  "sedimentacao_deposicaosemveg"
    t.boolean  "sedimentacao_deposicaocomveg"
    t.boolean  "sedimentacao_rochas"
    t.boolean  "poluicao_descargasDomesticas"
    t.boolean  "poluicao_descargasETAR"
    t.boolean  "poluicao_descargasIndustriais"
    t.boolean  "poluicao_descargasQuimicas"
    t.boolean  "poluicao_descargasAguasPluviais"
    t.boolean  "poluicao_presencaCriacaoAnimais"
    t.boolean  "poluicao_lixeiras"
    t.boolean  "poluicao_lixoDomestico"
    t.boolean  "poluicao_entulho"
    t.boolean  "poluicao_monstrosDomesticos"
    t.boolean  "poluicao_sacosDePlastico"
    t.boolean  "poluicao_latasMaterialFerroso"
    t.boolean  "poluicao_queimadas"
    t.string   "intervencoes_outras"
    t.string   "repteis_outro"
    t.string   "aves_outro"
    t.string   "mamiferos_outro"
    t.string   "peixes_outro"
    t.string   "fauna_outro"
    t.string   "flora_outro"
    t.string   "vegetacaoInvasora_outro"
    t.integer  "margem"
    t.json     "images"
    t.string   "idRio"
    t.integer  "irr_hidrogeomorfologia"
    t.integer  "irr_qualidadedaagua"
    t.integer  "irr_alteracoesantropicas"
    t.integer  "irr_corredorecologico"
    t.integer  "irr_participacaopublica"
    t.integer  "irr_organizacaoeplaneamento"
    t.integer  "irr"
    t.float    "lat"
    t.float    "lon"
    t.string   "nomeRio"
    t.integer  "edit_user_id"
    t.boolean  "validated"
  end

  add_index "form_irrs", ["user_id"], name: "index_form_irrs_on_user_id", using: :btree

  create_table "guardario_images", force: :cascade do |t|
    t.string   "image"
    t.datetime "created_at",   null: false
    t.datetime "updated_at",   null: false
    t.integer  "guardario_id"
  end

  add_index "guardario_images", ["guardario_id"], name: "index_guardario_images_on_guardario_id", using: :btree

  create_table "guardarios", force: :cascade do |t|
    t.string   "rio"
    t.integer  "user_id"
    t.datetime "created_at",  null: false
    t.datetime "updated_at",  null: false
    t.string   "local"
    t.string   "voar"
    t.string   "cantar"
    t.boolean  "parado"
    t.boolean  "beber"
    t.boolean  "cacar"
    t.boolean  "cuidarcrias"
    t.string   "alimentar"
    t.string   "outro"
    t.string   "nomeRio"
    t.float    "lat"
    t.float    "lon"
  end

  create_table "limpezas", force: :cascade do |t|
    t.string   "opcao"
    t.string   "resposta"
    t.datetime "created_at",   null: false
    t.datetime "updated_at",   null: false
    t.string   "categoria"
    t.integer  "categoria_id"
  end

  create_table "log_limpezas", force: :cascade do |t|
    t.string   "problema1"
    t.string   "problema2"
    t.string   "problema3"
    t.string   "problema4"
    t.string   "problema5"
    t.string   "problema6"
    t.string   "problema7"
    t.string   "problema8"
    t.string   "problema9"
    t.string   "problema10"
    t.string   "problema11"
    t.string   "problema12"
    t.string   "problema13"
    t.date     "cheia_data"
    t.string   "cheia_origem"
    t.integer  "cheia_perdas_monetarias"
    t.string   "cheia_destruicao"
    t.datetime "created_at",              null: false
    t.datetime "updated_at",              null: false
    t.integer  "user_id"
  end

  add_index "log_limpezas", ["user_id"], name: "index_log_limpezas_on_user_id", using: :btree

  create_table "reabilitacaos", force: :cascade do |t|
    t.datetime "created_at",           null: false
    t.datetime "updated_at",           null: false
    t.boolean  "definicao"
    t.boolean  "diagnostico"
    t.boolean  "prioritizacao"
    t.boolean  "objectivos"
    t.boolean  "solucoes"
    t.boolean  "elaboracao"
    t.boolean  "implementacao"
    t.boolean  "monitorizacao"
    t.boolean  "avaliacao"
    t.boolean  "correcao"
    t.boolean  "ppublica"
    t.boolean  "parcerias"
    t.boolean  "legislacao"
    t.boolean  "lei_agua"
    t.boolean  "directiva_agua"
    t.boolean  "directiva_inundacoes"
    t.boolean  "custo"
    t.json     "cronograma"
    t.boolean  "formacao"
    t.boolean  "emergencia"
    t.boolean  "reabilitacao"
    t.boolean  "revisao"
    t.integer  "user_id"
    t.string   "rio"
  end

  add_index "reabilitacaos", ["user_id"], name: "index_reabilitacaos_on_user_id", using: :btree

  create_table "report_images", force: :cascade do |t|
    t.string   "image"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.integer  "report_id"
  end

  add_index "report_images", ["report_id"], name: "index_report_images_on_report_id", using: :btree

  create_table "reports", force: :cascade do |t|
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.integer  "user_id"
    t.string   "rio"
    t.string   "descricao"
    t.string   "categoria"
    t.string   "motivo"
    t.float    "lat"
    t.float    "lon"
    t.string   "nome_rio"
  end

  add_index "reports", ["user_id"], name: "index_reports_on_user_id", using: :btree

  create_table "rota_point_images", force: :cascade do |t|
    t.string   "image"
    t.datetime "created_at",    null: false
    t.datetime "updated_at",    null: false
    t.integer  "rota_point_id"
  end

  add_index "rota_point_images", ["rota_point_id"], name: "index_rota_point_images_on_rota_point_id", using: :btree

  create_table "rota_points", force: :cascade do |t|
    t.string  "nome"
    t.string  "descricao"
    t.float   "lat"
    t.float   "lon"
    t.integer "ordem"
    t.integer "route_id"
  end

  create_table "routes", force: :cascade do |t|
    t.string   "nome"
    t.string   "descricao"
    t.string   "zona"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.boolean  "publicada"
  end

  create_table "trip_images", force: :cascade do |t|
    t.string   "image"
    t.datetime "created_at",    null: false
    t.datetime "updated_at",    null: false
    t.integer  "trip_point_id"
  end

  add_index "trip_images", ["trip_point_id"], name: "index_trip_images_on_trip_point_id", using: :btree

  create_table "trip_points", force: :cascade do |t|
    t.integer  "trip_id"
    t.string   "descricao"
    t.float    "lat"
    t.float    "lon"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "trips", force: :cascade do |t|
    t.integer  "user_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.string   "idRio"
    t.string   "nomeRio"
  end

  create_table "users", force: :cascade do |t|
    t.string   "nome"
    t.integer  "access"
    t.datetime "created_at",                          null: false
    t.datetime "updated_at",                          null: false
    t.string   "email",                  default: "", null: false
    t.string   "encrypted_password",     default: "", null: false
    t.string   "reset_password_token"
    t.datetime "reset_password_sent_at"
    t.datetime "remember_created_at"
    t.integer  "sign_in_count",          default: 0,  null: false
    t.datetime "current_sign_in_at"
    t.datetime "last_sign_in_at"
    t.inet     "current_sign_in_ip"
    t.inet     "last_sign_in_ip"
    t.string   "authentication_token"
    t.integer  "telef"
    t.string   "habilitacoes"
    t.string   "profissao"
    t.string   "formacao"
    t.integer  "distrito_id"
    t.integer  "concelho_id"
    t.integer  "permissoes"
  end

  add_index "users", ["authentication_token"], name: "index_users_on_authentication_token", using: :btree
  add_index "users", ["email"], name: "index_users_on_email", unique: true, using: :btree
  add_index "users", ["reset_password_token"], name: "index_users_on_reset_password_token", unique: true, using: :btree

  add_foreign_key "concelhos", "distritos"
  add_foreign_key "form_irr_images", "form_irrs"
  add_foreign_key "form_irrs", "users"
  add_foreign_key "guardario_images", "guardarios"
  add_foreign_key "log_limpezas", "users"
  add_foreign_key "reabilitacaos", "users"
  add_foreign_key "report_images", "reports"
  add_foreign_key "reports", "users"
  add_foreign_key "rota_point_images", "rota_points"
  add_foreign_key "rota_points", "routes"
  add_foreign_key "trip_images", "trip_points"
  add_foreign_key "trip_points", "trips"
end
