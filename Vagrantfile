# -*- mode: ruby -*-
# vi: set ft=ruby :

boxes = [
  {
    :name => "cnvr1",
    :scriptProvision => "scripts/web-provision.sh",
    :scriptInit => "scripts/web-up.sh",
    :eth1 => "172.28.128.3",
    :eth2 => "172.28.11.3",
    :port => 3001,
    :args => '172.28.11.2:2181 http://172.28.11.3:3000'
  },
  {
    :name => "cnvr2",
    :scriptProvision => "scripts/web-provision.sh",
    :scriptInit => "scripts/web-up.sh",
    :eth1 => "172.28.128.4",
    :eth2 => "172.28.11.4",
    :port => 3002,
    :args => '172.28.11.2:2182 http://172.28.11.4:3000'
    
  },
  {
    :name => "cnvr3",
    :scriptProvision => "scripts/web-provision.sh",
    :scriptInit => "scripts/web-up.sh",
    :eth1 => "172.28.128.5",
    :eth2 => "172.28.11.5",
    :port => 3003,
    :args => '172.28.11.2:2183 http://172.28.11.5:3000'
    
  }
]

Vagrant.configure(2) do |config|
 

  config.vm.box = "ubuntu/trusty64"
  
  # Zookeeper
  config.vm.define :zk, primary: true do |zk_config|
    zk_config.vm.box = "ubuntu/trusty64"
    zk_config.vm.hostname = 'zk'
    zk_config.vm.network :forwarded_port, guest: 2181, host: 2181
    zk_config.vm.network :forwarded_port, guest: 2182, host: 2182
    zk_config.vm.network :forwarded_port, guest: 2183, host: 2183
    zk_config.vm.network "private_network", ip: "172.28.11.2",  netmask: "255.255.255.0"
    zk_config.vm.synced_folder "./zk", "/cnvr/scripts"
    zk_config.vm.provision :shell, :path => "scripts/zk-provision.sh"
    zk_config.vm.provision :shell, :path => "scripts/zk-up.sh", run: 'always'

  end

  
  boxes.each do |opts|
    config.vm.define opts[:name] do |config|
      config.vm.hostname = opts[:name]
      config.vm.network :forwarded_port, guest: 3000, host: opts[:port]
      config.vm.network "private_network", ip: opts[:eth1],  netmask: "255.255.255.0"
      config.vm.network "private_network", ip: opts[:eth2],  netmask: "255.255.255.0"
      # config.vm.network "private_network",  type: "dhcp" # http://172.28.128.3:3000/
      config.vm.provision "shell", path: opts[:scriptProvision]
      config.vm.synced_folder ".", "/cnvr"
      config.vm.provision "shell", path: opts[:scriptInit], run: 'always', args: opts[:args]
      
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

