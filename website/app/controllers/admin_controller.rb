class AdminController < ApplicationController
  before_filter { |c| c.authenticate_rights current_user.id }

  def index
    @users = User.all.paginate(:page => params[:page], :per_page => 10).order('created_at')
  end

  def authenticate_rights(user_id)
    user = User.find(user_id)
    redirect_to new_user_session_path unless user.permissoes == 6
  end

end