# -*- mode: ruby -*-
# vi: set ft=ruby :

boxes = [
  {
    :name => "cnvr1",
    :scriptProvision => "scripts/script.sh",
    :scriptInit => "up.sh",
    :eth1 => "172.28.128.3",
    :eth2 => "192.168.34.11",
  },
  {
    :name => "cnvr2",
    :scriptProvision => "scripts/script.sh",
    :scriptInit => "up.sh",
    :eth1 => "172.28.128.4",
    :eth2 => "192.168.34.12",
  },
  {
    :name => "cnvr3",
    :scriptProvision => "scripts/script.sh",
    :scriptInit => "up.sh",
    :eth1 => "172.28.128.5",
    :eth2 => "192.168.34.13",
  }
]

Vagrant.configure(2) do |config|


  config.vm.box = "ubuntu/trusty64"

  boxes.each do |opts|
    config.vm.define opts[:name] do |config|
      config.vm.hostname = opts[:name]
      config.vm.network "private_network", ip: opts[:eth1],  netmask: "255.255.255.0"
      # config.vm.network "private_network",  type: "dhcp" # http://172.28.128.3:3000/
      config.vm.provision "shell", path: opts[:scriptProvision]
      config.vm.synced_folder ".", "/cnvr"
      config.vm.provision "shell", path: opts[:scriptInit], run: 'always'
    end
  end

  # HAPROXY
  config.vm.define :haproxy, primary: true do |haproxy_config|
    haproxy_config.vm.box = "precise32"
    haproxy_config.vm.box_url = "http://files.vagrantup.com/precise32.box"
    haproxy_config.vm.provider "virtualbox" do |v|
      v.customize ["modifyvm", :id, "--memory", 256]
    end
    haproxy_config.vm.hostname = 'haproxy'
    haproxy_config.vm.network :forwarded_port, guest: 8080, host: 8080
    haproxy_config.vm.network :forwarded_port, guest: 80, host: 8081

    haproxy_config.vm.network :private_network, ip: "172.28.128.10",  netmask: "255.255.255.0"
    haproxy_config.vm.provision :shell, :path => "scripts/haproxy-setup.sh", run: 'always'

  end
end

