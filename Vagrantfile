# -*- mode: ruby -*-
# vi: set ft=ruby :

boxes = [
  {
    :name => "cnvr1",
    :script => "script.sh",
    :eth1 => "172.28.128.3",
    :eth2 => "192.168.34.11",
  },
  {
    :name => "cnvr2",
    :script => "script.sh",
    :eth1 => "172.28.128.4",
    :eth2 => "192.168.34.12",
  },
  {
    :name => "cnvr3",
    :script => "script.sh",
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
      config.vm.provision "shell", path: opts[:script]
      config.vm.synced_folder ".", "/cnvr"
    end
  end
  # config.vm.define :haproxy, primary: true do |haproxy_config|

  #   haproxy_config.vm.hostname = 'haproxy'
  #   haproxy_config.vm.network :forwarded_port, guest: 8080, host: 8080
  #   haproxy_config.vm.network :forwarded_port, guest: 80, host: 8081

  #   haproxy_config.vm.network :private_network, ip: "172.28.128.10"
  #   haproxy_config.vm.provision :shell, :path => "haproxy-setup.sh"

  # end
end

