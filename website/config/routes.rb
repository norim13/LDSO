Rails.application.routes.draw do

  resources :routes
  resources :rotas

  get 'rio/:id' => 'rio#show', as: :rio, :id => /.*/

  get 'search' => 'searchrios#display'
  get 'profile' => 'profile#display'
  get 'home' => 'home#homepage'
  get 'contactos' => 'about#about'
  get 'documentos' => 'documentos_relacionados#documentos'
  get 'distritos' => 'concelho#getDistritos'
  get 'concelhos' => 'concelho#getConcelhosFromDistrito'
  get 'limpeza' => 'limpeza#show'
  get 'respostas' => 'limpeza#getRespostas'
  get 'adminpanel' => 'admin#index'
  post 'submitProblemasAction' => 'limpeza#submitProblemas'
  post 'changePermissions' => 'users#updatepermissions'

  #info pages
  get 'limpeza/info' => 'limpeza#info'
  get 'reabilitacao/info' => 'reabilitacaos#info'
  get 'form_irr/info' => 'form_irrs#info'
  get 'report/info' => 'reports#info'
  get 'lab_rios/info' => 'lab_rios#info'
  get 'participacao_publica/info' => 'participacao_publica#info'
  get 'projetos/info' => 'projetos#info'

  resources :form_irr_image
  resources :reabilitacaos
  resources :guardarios, only: [:index, :show, :new, :create, :destroy]
  get 'meusguardarios' => 'guardarios#getMine'
  resources :reports, only: [:index, :show, :new, :create, :destroy]
  resources :form_irrs
  # The priority is based upon order of creation: first created -> highest priority.
  # See how all your routes lay out with "rake routes".

  devise_for :users, :controllers => {registrations: 'registrations'}

  devise_scope :user do
    get '/users/sign_out' => 'devise/sessions#destroy'
  end

  root 'home#homepage'

  namespace :api do
    namespace :v1 do
      resources :problems
      resources :services
      devise_scope :user do
        post "/sign_in", :to => 'sessions#create'
        post "/sign_up", :to => 'registrations#create'
        delete "/sign_out", :to => 'sessions#destroy'
      end
    end
    namespace :v2 do
      post "/form_irrs", :to => 'form_irrs#create'
      get "/form_irrs", :to => 'form_irrs#getMyForms'
      patch "/form_irrs/:id", :to => 'form_irrs#update'
      delete "/form_irrs/:id", :to => 'form_irrs#destroy'

      post "/guardarios", :to => 'guardarios#create'
      post "/reports", :to => 'reports#create'

      get "/limpezas/:opcao", :to => 'limpezas#getRespostas', :opcao => /.*/
      post "/limpezas", :to => 'limpezas#submitProblemas'

      get "/users", :to => 'users#getUser'
      delete "/users", :to => 'users#destroy'
      patch "/users", :to => 'users#update'
    end
  end
end
