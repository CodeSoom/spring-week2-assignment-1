import MyTemplate from './index.marko';

MyTemplate.renderSync({ name: 'Marko' }).appendTo(document.body);