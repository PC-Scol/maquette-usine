Vagrant.configure("2") do |config|

config.hostmanager.enabled = true
config.hostmanager.manage_host = true
config.hostmanager.manage_guest = true
config.hostmanager.ignore_private_ip = false
config.hostmanager.include_offline = true

config.vm.define "gitlab" do |gitlab|
    gitlab.vm.box = "ubuntu/trusty64"
    gitlab.vm.network "private_network", ip: "192.168.50.10"
    gitlab.hostmanager.aliases = %w(mattermost)
    gitlab.vm.provider "virtualbox" do |v|
        v.memory = 2560
    end
    gitlab.vm.provision "ansible_local" do |ansible|
        ansible.playbook = "provisioning/gitlab/playbook.yml"
        ansible.sudo = true
        ansible.galaxy_role_file = 'provisioning/gitlab/requirements.yml'
        ansible.galaxy_roles_path = '/home/vagrant/ansible_galaxy'
    end
  end

config.vm.define "jenkins_master" do |jenkins_master|
    jenkins_master.vm.box = "ubuntu/xenial64"
    jenkins_master.vm.network "private_network", ip: "192.168.50.11"
    jenkins_master.vm.provider "virtualbox" do |v|
        v.memory = 1024
    end
    jenkins_master.vm.provision "ansible_local" do |ansible|
        ansible.playbook = "provisioning/jenkins_master/playbook.yml"
        ansible.sudo = true
        ansible.galaxy_role_file = 'provisioning/jenkins_master/requirements.yml'
        ansible.galaxy_roles_path = '/tmp/ansible_galaxy'
    end
  end
end