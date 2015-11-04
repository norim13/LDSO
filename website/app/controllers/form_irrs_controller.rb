class FormIrrsController < ApplicationController
  #fazer validacoes em todos!
  #validates :tipoDeVale, presence: true, length: { minimum: 5 }
  before_filter :authenticate_user!

  def index
    @form_irrs = current_user.form_irrs
  end

  def new
    @form_irr = FormIrr.new
  end

  def show
    @form_irr = FormIrr.find(params[:id])
    if @form_irr.user_id != current_user.id
      render 'noaccess'
    end
  end

  def edit
    @form_irr = FormIrr.find(params[:id])
    if @form_irr.user_id != current_user.id
      render 'noaccess'
    end
  end

  def create
    @form_irr = FormIrr.new(form_irr_params)
    @form_irr.user_id = current_user.id

    if @form_irr.save
      redirect_to @form_irr
    else
      render 'new'
    end
  end

  def update
    @form_irr = FormIrr.find(params[:id])

    if @form_irr.update(form_irr_params)
      redirect_to @form_irr
    else
      render 'edit'
    end
  end

  private
  def form_irr_params
    params.require(:form_irr).permit(:tipoDeVale,:perfilDeMargens,:larguraDaSuperficieDaAgua,:profundidadeMedia,:seccao,:velocidadeMedia,:caudal,:substratoDasMargens_soloArgiloso,:substratoDasMargens_arenoso,:substratoDasMargens_pedregoso,:substratoDasMargens_rochoso,
      :substratoDasMargens_artificialPedra,:substratoDasMargens_artificialBetao,:substratoDoLeito_blocoseRocha,:substratoDoLeito_calhaus,:substratoDoLeito_cascalho,:substratoDoLeito_areia,:substratoDoLeito_limo,:substratoDoLeito_solo,:substratoDoLeito_artificial,:substratoDoLeito_naoEVisivel,
      :estadoGeraldaLinhadeAgua,:erosao,:sedimentacao,:pH,:condutividade,:temperatura,:nivelDeOxigenio,:percentagemDeOxigenio,:nitratos,:nitritos,:transparencia,:oleo,:espuma,:esgotos,:impurezas,:sacosDePlastico,:latas,:indiciosNaAgua_outros,:corDaAgua,:odorDaAgua,:planarias,:hirudineos,
      :oligoquetas,:simulideos,:quironomideos,:ancilideo,:limnideo,:bivalves,:patasNadadoras,:pataLocomotoras,:trichopteroS,:trichopteroC,:odonata,:heteropteros,:plecopteros,:baetideo,:cabecaPlanar,:crustaceos,:acaros,:pulgaDeAgua,:insetos,:megalopteres,:intervencoes_edificios ,
      :intervencoes_pontes,:intervencoes_limpezasDasMargens,:intervencoes_estabilizacaoDeMargens,:intervencoes_modelacaoDeMargensNatural,:intervencoes_modelacaoDeMargensArtificial,:intervencoes_barragem,:intervencoes_diques,:intervencoes_rioCanalizado,:intervencoes_rioEntubado,
      :intervencoes_esporoes ,:intervencoes_paredoes,:intervencoes_tecnicasDeEngenhariaNatural,:intervencoes_outras,:ocupacao_florestaNatural,:ocupacao_florestaPlantadas,:ocupacao_matoAlto,:ocupacao_matoRasteiro,:ocupacao_pastagem,:ocupacao_agricultura,:ocupacao_espacoAbandonado,
      :ocupacao_jardins,:ocupacao_zonaEdificada,:ocupacao_zonaIndustrial,:ocupacao_ruas,:ocupacao_entulho,:patrimonio_moinho,:patrimonio_acude,:patrimonio_microAcude1,:patrimonio_microAcude2,:patrimonio_barragem,:patrimonio_levadas,:patrimonio_pesqueiras,:patrimonio_escadasDePeixe,
      :patrimonio_poldras,:patrimonio_pontesSemPilar,:patrimonio_pontesComPilar,:patrimonio_passagemAVau,:patrimonio_barcos,:patrimonio_cais,:patrimonio_igreja,:patrimonio_solares,:patrimonio_nucleoHabitacional,:patrimonio_edificiosParticulares,:patrimonio_edificiosPublicos,
      :patrimonio_ETA,:patrimonio_descarregadoresDeAguasPluviais,:patrimonio_coletoresSaneamento,:patrimonio_defletoresArtificiais,:patrimonio_motaLateral,:poluicao_descargasDomesticas,:poluicao_descargasETAR,:poluicao_descargasIndustriais,:poluicao_descargasQuimicas,
      :poluicao_descargasAguasPluviais,:poluicao_presencaCriacaoAnimais,:poluicao_lixeiras,:poluicao_lixoDomestico,:poluicao_entulho,:poluicao_monstrosDomesticos,:poluicao_sacosDePlastico,:poluicao_latasMaterialFerroso,:poluicao_queimadas,:salamandraLusitanica,:salamandraPintasAmarelas,
      :tritaoVentreLaranja,:raIberica,:raVerde,:sapoComum,:lagartoDeAgua,:cobraAguaDeColar,:cagado,:repteis_outro,:guardaRios,:garcaReal,:melroDeAgua,:galinhaDeAgua,:patoReal,:tentilhaoComum,:chapimReal,:aves_outro,:lontras,:morcegosDeAgua,:toupeiraDaAgua,:ratoDeAgua,:ouricoCacheiro,
      :armilho,:mamiferos_outro,:enguia,:lampreia,:salmao,:truta,:bogaPortuguesa,:bogaDoNorte,:peixes_outro,:percaSol,:tartarugaDaFlorida,:caranguejoPeludoChines,:gambusia,:mustelaVison,:lagostimVermelho,:trutaArcoIris,:achiga,:fauna_outro,:salgueiral,:amial,:freixal,:choupal,:ulmeiral,
      :sanguinos,:ladual,:tramazeiras,:carvalhal,:sobreiral,:azinhal,:flora_outro,:conservacaoBosqueRibeirinho,:silvas,:ervaDaFortuna,:plumas,:lentilhaDaAgua,:pinheirinha,:jacintoDeAgua,:vegetacaoInvasora_outro,:obstrucaoDoLeitoMargens,:disponibilizacaoDeInformacao,:envolvimentoPublico,
      :acao,:legislacao,:estrategia,:gestaoDasIntervencoes)
end
end