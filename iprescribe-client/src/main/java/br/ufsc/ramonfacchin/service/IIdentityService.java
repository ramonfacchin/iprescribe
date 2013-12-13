package br.ufsc.ramonfacchin.service;

import javax.ejb.Remote;

import br.ufsc.ramonfacchin.common.annotation.DefaultBeanName;
import br.ufsc.ramonfacchin.entity.Identity;

@Remote
@DefaultBeanName("IdentityService")
public interface IIdentityService extends IService<Identity> {

}
