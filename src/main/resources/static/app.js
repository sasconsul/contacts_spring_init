/**
 * Created by sasconsul on 7/24/16.
 */
const React = require('react');
const client = require('./client');

const follow = require('./follow'); // function to hop multiple links by "rel"

const root = '/api';

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {contacts: [], attributes: [], pageSize: 2, links: {}};
        this.updatePageSize = this.updatePageSize.bind(this);
        this.onCreate = this.onCreate.bind(this);
        this.onUpdate = this.onUpdate.bind(this);
        this.onDelete = this.onDelete.bind(this);
        this.onNavigate = this.onNavigate.bind(this);
    }

    loadFromServer(pageSize) {
        follow(client, root, [
            {rel: 'contacts', params: {size: pageSize}}]
        ).then(contactCollection => {
            return client({
                method: 'GET',
                path: contactCollection.entity._links.profile.href,
                headers: {'Accept': 'application/schema+json'}
            }).then(schema => {
                this.schema = schema.entity;
                this.links = contactCollection.entity._links;
                return contactCollection;
            });
        }).then(contactCollection => {
            return contactCollection.entity._embedded.contacts.map(contact =>
                client({
                    method: 'GET',
                    path: contact._links.self.href
                })
            );
        }).then(contactPromises => {
            return when.all(contactPromises);
        }).done(contacts => {
            this.setState({
                contacts: contacts,
                attributes: Object.keys(this.schema.properties),
                pageSize: pageSize,
                links: this.links
            });
        });
    }

    componentDidMount() {
        this.loadFromServer(this.state.pageSize);
    }

    render() {
        return (
            <ContactList contacts={this.state.contacts}/>
    )
    }
}

class ContactList extends React.Component{
    render() {
        var contacts = this.props.contacts.map(contact =>
            <Contact key={contact._links.self.href} contact={contact}/>
        );
        return (
            <table>
                <tr>
                    <th>Name</th>
                    <th>EMail</th>
                    <th>Profession</th>
                    <th>Employed</th>
                </tr>
                {contacts}
            </table>
        )
    }
}

class Contact extends React.Component{
    render() {
        return (
            <tr>
                <td>{this.props.contact.name}</td>
                <td>{this.props.contact.email}</td>
                <td>{this.props.contact.profession}</td>
                <td>{this.props.contact.employed}</td>
            </tr>
        )
    }
}

React.render(
    <App />,
    document.getElementById('react')
)
